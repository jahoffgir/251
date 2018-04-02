import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class Six Of Queens is the client main program for the Six Of Queens network
 * game.
 *
 * @author  Jahongir Amirkulov
 * @version 04/01/18
 */
public class TicTacToe
	{

	/**
	 * Main program.
	 */
	public static void main (String[] args) {
		// Parse command line arguments.
		if (args.length != 3) usage();
		String host = args[0];
		int port = parseInt (args[1], "<port>");
		String name = args[2];

		try {
			// Open socket connection to server.
			Socket socket = new Socket();
			socket.connect (new InetSocketAddress (host, port));

			// Set up view and model proxy.
			SixOfQueensView view = SixOfQueensView.create (name);
			ModelProxy proxy = new ModelProxy (socket);
			view.setListener (proxy);
			proxy.setListener (view);

			// Inform server that a player has joined.
			proxy.join (view, name);
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
		System.err.printf ("TicTacToe: %s%n", msg);
		usage();
	}

	/**
	 * Print a usage message and terminate.
	 */
	private static void usage() {
		System.err.println ("Usage: java TicTacToe <host> <port> <name>");
		System.exit (1);
	}

	/**
	 * Print an I/O error message and terminate.
	 *
	 * @param  exc  Exception.
	 */
	private static void error (IOException exc) {
		System.err.println ("TicTacToe: I/O error");
		exc.printStackTrace (System.err);
		System.exit (1);
	}
}
