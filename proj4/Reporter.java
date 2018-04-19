/**
 * 
 * @author Jahongir Amirkulov
 * @version 04/08/18
 */

public class Reporter {
       // main method
       public static void main(String[] args) {

        // number of arguements
        if (args.length != 3) usage();

        try {
            // User input
            String rhost = args[0];
            int rport = Integer.parseInt(args[1]);
            String privateKetFile = args[2];

            // TODO rename mailbox to something else
            DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress (rport, rport));
            
            

        } catch (Exception e) {
            System.err.println("Illegal arguement.");
            usage();
        }

    }

    /**
    * Print a usage message and exit.
    */
    private static void usage() {
        System.err.println("Usage: ava Reporter <rhost> <rport> <privatekeyfile>");
        System.exit(1);
    }
}