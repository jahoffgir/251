import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
/**
 * Class ModelProxy provides the model proxy for the SixQueen Game. It
 * implements the client side of the client-server network communication.
 *
 * @author  Alan Kaminsky
 * @author  Jahongir Amirkulov
 * @version 04/05/18
 */

public class ModelProxy implements ViewListener {

	// Hidden data members.

	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private ModelListener listener;

	// Exported constructors.

	/**
	 * Construct a new model proxy.
	 *
	 * @param  socket  Socket.
	 *
	 * @exception  IOException
	 *     Thrown if an I/O error occurred.
	 */
	public ModelProxy(Socket socket) {
		try {
			this.socket = socket;
			socket.setTcpNoDelay (true);
			out = new DataOutputStream (socket.getOutputStream());
			in = new DataInputStream (socket.getInputStream());
		} catch (IOException exc) {
			error (exc);
		}
	}

	// Exported operations.

	/**
	 * Set the model listener object for this model proxy.
	 *
	 * @param  listener  Model listener.
	 */
	public void setListener(ModelListener listener) {
		this.listener = listener;
		new ReaderThread() .start();
	}

	/**
	 * Report that a player joined the game.
	 *
	 * @param  view  View object making the report.
	 * @param  name  Player's name.
	 */
	public void join(ModelListener view, String name) {
		try {
			out.writeByte ('J');
			out.writeUTF (name);
			out.flush();
		}
		catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that a square was chosen.
	 *
	 * @param  view  View object making the report.
	 * @param  i     Square index.
	 */
	public void squareChosen(ModelListener view, int i, int j) {
		try {
			out.writeByte ('S');
			out.writeByte (i);
			out.writeByte (j);
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that a new game was requested.
	 *
	 * @param  view  View object making the report.
	 */
	public void newGame(ModelListener view) {
		try {
			out.writeByte ('G');
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that the player quit.
	 *
	 * @param  view  View object making the report.
	 */
	public void quit(ModelListener view) {
		try {
			out.writeByte ('Q');
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	// Hidden helper classes.

	/**
	 * Class ReaderThread receives messages from the network, decodes them, and
	 * invokes the proper methods to process them.
	 */
	private class ReaderThread extends Thread {
		/**
		 * Run the reader thread.
		 */
		public void run() {
			int op, i, j;
			boolean v;
			Mark mark;
			String name;
			try {
				for (;;) {
					op = in.readByte();
					switch (op) {
						case 'N':
							listener.newGame();
							break;
						case 'E':
							i = in.readByte();
							j = in.readByte();
							listener.setQueen(i, j);
							break;
						case 'V':
							i = in.readByte();
							j = in.readByte();
							listener.setVisible(i, j);
							break;
						case 'P':
							listener.waitingForPartner();
							break;
						case 'T':
							listener.yourTurn();
							break;
						case 'U':
							name = in.readUTF();
							listener.otherTurn (name);
							break;
						case 'W':
							listener.youWin();
							break;
						case 'X':
							name = in.readUTF();
							listener.otherWin (name);
							break;
						case 'Q':
							listener.quit();
							break;
						default:
							error ("Bad message");
							break;
					}
				}
			} catch (EOFException exc) {

			} catch (IOException exc) {
				error (exc);
			} finally {
				try {
					socket.close();
				} catch (IOException exc) {

				}
			}
		}
	}

	// Hidden operations.
	/**
	 * Print an error message and terminate the program.
	 *
	 * @param  msg  Message.
	 */
	private static void error(String msg) {
		System.err.printf ("ModelProxy: %s%n", msg);
		System.exit (1);
	}

	/**
	 * Print an I/O error message and terminate the program.
	 */
	private static void error(IOException exc) {
		System.err.println ("ModelProxy: I/O error");
		exc.printStackTrace (System.err);
		System.exit (1);
	}
}