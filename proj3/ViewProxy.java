import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Class ViewProxy provides the view proxy for the SixQueen Game. It
 * implements the server side of the client-server network communication.
 *
 * @author  Alan Kaminsky
 * @author  Jahongir Amirkulov
 * @version 04/05/18
 */
public class ViewProxy implements ModelListener {

	// Hidden data members.

	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private ViewListener listener;

	// Exported constructors.

	/**
	 * Construct a new view proxy.
	 *
	 * @param  socket  Socket.
	 *
	 * @exception  IOException
	 *     Thrown if an I/O error occurred.
	 */
	public ViewProxy(Socket socket) {
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
	 * Set the view listener object for this view proxy.
	 *
	 * @param  listener  Model listener.
	 */
	public void setListener(ViewListener listener) {
		this.listener = listener;
		new ReaderThread() .start();
	}

	/**
	 * Report that a new game was started.
	 */
	public void newGame() {
		try {
			out.writeByte ('N');
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * 
	 * Sets the queen
	 * @param 	i	row
	 * @param 	j	col
	 * 
	 */
	public void setQueen(int i, int j) {
		try {
			out.writeByte('E');
			out.writeByte(i);
			out.writeByte(j);
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * 
	 * Sets the cell invisible
	 * 
	 * @param 	i 	row
	 * @param 	j 	col
	 */
	public void setVisible(int i, int j) {
		try {
			out.writeByte('V');
			out.writeByte(i);
			out.writeByte(j);
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that the player is waiting for a partner.
	 */
	public void waitingForPartner() {
		try {
			out.writeByte ('P');
			out.flush();
		}
		catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that it's the player's turn.
	 */
	public void yourTurn() {
		try {
			out.writeByte ('T');
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that it's the other player's turn.
	 *
	 * @param  name  Other player's name.
	 */
	public void otherTurn(String name) {
		try {
			out.writeByte ('U');
			out.writeUTF (name);
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that the player wins.
	 */
	public void youWin() {
		try {
			out.writeByte ('W');
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that the other player wins.
	 *
	 * @param  name  Other player's name.
	 */
	public void otherWin(String name) {
		try {
			out.writeByte ('X');
			out.writeUTF (name);
			out.flush();
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Report that a player quit.
	 */
	public void quit() {
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
	private class ReaderThread
		extends Thread
		{
		/**
		 * Run the reader thread.
		 */
		public void run()
			{
			int op, i, j;
			String name;
			try {
				for (;;) {
					op = in.readByte();
					switch (op) {
						case 'J':
							name = in.readUTF();
							listener.join (ViewProxy.this, name);
							break;
						case 'S':
							i = in.readByte();
							j = in.readByte();
							listener.squareChosen (ViewProxy.this, i, j);
							break;
						case 'G':
							listener.newGame (ViewProxy.this);
							break;
						case 'Q':
							listener.quit (ViewProxy.this);
							break;
						default:
							error ("Bad message");
							break;
					}
				}
			}
			catch (EOFException exc) {

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
		System.err.printf ("ViewProxy: %s%n", msg);
		System.exit (1);
	}

	/**
	 * Print an I/O error message and terminate the program.
	 *
	 * @param  exc  Exception.
	 */
	private static void error(IOException exc) {
		System.err.println ("ViewProxy: I/O error");
		exc.printStackTrace (System.err);
		System.exit (1);
	}

}