/**
 * 
 * A Goldbach number is an even number that is the sum of two primes. The
 * Goldbach Conjecture, posed by German mathematician Christian Goldbach in 1742
 * and as yet not proven or disproven, is that every even number greater than 4 
 * is a Goldbach number.
 *
 * @author Jahongir Amirkulov
 * @version 02/26/18
 *
 */

public class Goldbach {
   
    /**
     *
     * Main Program
     *
     */
    public static void main(String [] args) throws Exception {
        // Validate command line arguments.
        if (args.length < 1) {
            System.err.println("Incorrect number of arguements");
            System.exit(1);
        }

        try {
            // even number n
            int n = Integer.parseInt(args[0]);

            // checking for even number
            if (n % 2 != 0) { 
                System.err.println("<n> must be an even number > 4");
                System.exit(1);
            }

            parallelFor (0, n - 1) .exec (new Loop() {
                /**
                 * Start method
                 */
                public void start() {

                }

                /**
                 * Run method
                 * @param i 
                 */
                public void run (int i) {
                    
                }
            });
        } 
        // Checking the type of the args
        catch (IllegalArgumentException e) {
            System.err.println("Argument is not an int. Exiting.");
            System.exit(1);
        }
    }
}

