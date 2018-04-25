import java.net.DatagramSocket;
import java.net.InetSocketAddress;
/**
 * 
 * Class Reporter that will recieve messages from Leakers and will decode it. 
 * 
 * @author Jahongir Amirkulov
 * @version 04/25/18
 * 
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
            String privateKeyFile = args[2];
            DatagramSocket reporter = new DatagramSocket(new InetSocketAddress (rhost, rport));
            ReporterModel rm = new ReporterModel(privateKeyFile);
            LeakerProxy rp = new LeakerProxy(reporter, rm);
            rp.start();  
        } catch (Exception e) {
            System.err.println("Error in the Reporter.");
            System.exit(1);
        }
    }

    /**
    * Print a usage message and exit.
    */
    private static void usage() {
        System.err.println("Usage: java Reporter <rhost> <rport> <privatekeyfile>");
        System.exit(1);
    }
}
