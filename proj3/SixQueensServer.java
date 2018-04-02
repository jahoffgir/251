/**
 *  Six Queens is a two-player game where the players take turns placing chess 
 *  queens on a 6×6 chess board. Each queen must be placed so that it cannot be
 *  attacked by any previously-placed queen. Recall that a queen can attack any 
 *  number of squares horizontally, vertically, or diagonally. The winner is 
 *  the player who places the last queen, such that all remaining squares are 
 *  attacked by the queens on the board.
 *
 *  @author     Jahongir Amirkulov
 *  @version    03/28/18
 *
 */ 

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class SixQueensServer is the server main program for the Six Queens server network
 * game.
 *
 */
public class SixQueensServer {

	/**
	 * Main program.
	 */
	public static void main (String[] args) {
		// Parse command line arguments.
		if (args.length != 2) usage();
		String host = args[0];
		int port = parseInt (args[1], "<port>");

		try {
			// Listen for connections from clients.
			ServerSocket serversocket = new ServerSocket();
			serversocket.bind (new InetSocketAddress (host, port));

			// Session management logic.
			SixQueensModel model = null;
			for (;;) {
				Socket socket = serversocket.accept();
				ViewProxy proxy = new ViewProxy (socket);
				if (model == null || model.isFinished()) {
					model = new SixQueensModel();
					proxy.setListener (model);
				} else {
					proxy.setListener (model);
					model = null;
				}
			}
		} catch (IOException exc) {
			error (exc);
		}
	}

	/**
	 * Parse an integer.
	 *
	 * @param  s      String to parse.
	 * @param  label  Error message label.
	 *
	 * @return  Integer value.
	 */
	private static int parseInt (String s, String label) {
		int i = 0;
		try {
			i = Integer.parseInt (s);
		} catch (NumberFormatException exc) {
			errorUsage (String.format ("%s = \"%s\" illegal", label, s));
		}
		return i;
	}

	/**
	 * Print an error message and a usage message and terminate.
	 *
	 * @param  msg  Message.
	 */
	private static void errorUsage (String msg) {
		System.err.printf ("TicTacToeServer: %s%n", msg);
		usage();
	}

	/**
	 * Print a usage message and terminate.
	 */
	private static void usage() {
		System.err.println ("Usage: java TicTacToeServer <host> <port>");
		System.exit (1);
	}

	/**
	 * Print an I/O error message and terminate.
	 *
	 * @param  exc  Exception.
	 */
	private static void error (IOException exc) {
		System.err.println ("TicTacToeServer: I/O error");
		exc.printStackTrace (System.err);
		System.exit (1);
	}
}
