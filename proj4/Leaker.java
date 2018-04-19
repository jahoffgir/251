/**
 * 
 * @author Jahongir Amirkulov
 * @version 04/08/18
 */

 public class Leaker {

    // main method
    public static void main(String[] args) {
        // number of arguements
        if (args.length != 6) usage();

        try {
            // User input
            String rhost = args[0];
            int rport = Integer.parseInt(args[1]);
            String lhost = args[2];
            int lport = Integer.parseInt(args[3]);
            String publicKetFile = args[4];
            String message = args[5];
            DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress (rport, rport));
            
            // TODO FIX THIS
            StationProxy proxy = new StationProxy (mailbox, new InetSocketAddress (lhost, lport));

            // TODO pass the data to other classes
        } catch (Exception e) {
            System.err.println("Illegal arguement.");
            usage();
        }

    }

    /**
    * Print a usage message and exit.
    */
    private static void usage() {
        System.err.println ("Usage: java Leaker <rhost> <rport> <lhost> <lport> <publickeyfile> <message>");
        System.exit (1);
    }
 }