/**
 *  Six Queens is a two-player game where the players take turns placing chess 
 *  queens on a 6×6 chess board. Each queen must be placed so that it cannot be
 *  attacked by any previously-placed queen. Recall that a queen can attack any 
 *  number of squares horizontally, vertically, or diagonally. The winner is 
 *  the player who places the last queen, such that all remaining squares are 
 *  attacked by the queens on the board.
 *
 *  @author Jahongir Amirkulov
 *  @version 03/28/18
 *
 */ 

public class SixQueensServer {
    /**
	 * Parse an integer.
	 *
	 * @param  s      String to parse.
	 * @param  label  Error message label.
	 *
	 * @return  Integer value.
	 */
	private static int parseInt (String s, String label){
		int i = 0;
		try {
			i = Integer.parseInt (s);
		} catch (NumberFormatException exc) {
			errorUsage (String.format ("%s = \"%s\" illegal", label, s));
		}
		return i;
	}

    
    public static void main(String [] args) {
        // Parse command line arguments.
		if (args.length != 2) {
            System.err.println("Incorrect number of arguements");
        }

        String host = args[0];
		int port = parseInt (args[1], "<port>");
        try
			{
			// Listen for connections from clients.
			ServerSocket serversocket = new ServerSocket();
			serversocket.bind (new InetSocketAddress (host, port));

			// Session management logic.
			for (;;)
				{
				Socket socket = serversocket.accept();
				ViewProxy proxy = new ViewProxy (socket);
				PasswordCrackModel model = new PasswordCrackModel();
				model.setModelListener (proxy);
				proxy.setViewListener (model);
				}
			}
		catch (Exception exc)
			{
			error (exc);
			}
		}


    }
}
