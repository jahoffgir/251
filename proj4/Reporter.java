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
            String privateKeyFile = args[2];
            DatagramSocket reporter = new DatagramSocket(new InetSocketAddress (rport, rport));
            ReportProxy rp = new ReportProxy(reporter);
            ReporterModel rm = new ReporterModel(privateKeyFile);
            rp.setListener(rm);
        } catch (Exception e) {
            System.err.println("Illegal arguement.");
            usage();
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