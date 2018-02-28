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
import edu.rit.pj2.*;
import edu.rit.pj2.IntParallelForLoop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.IntVbl;
public class Goldbach extends Task{
  
    // Stores all of the prime numbers that equal to the input
    IntVbl count;

    /**
     * Main Program
     */
    public void main(String [] args) throws Exception {
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
            count = new IntVbl.Sum(0);            
            parallelFor (0, n - 1) .exec (new Loop() {
                IntVbl thrCount;
                /**
                 * Start method
                 */
                public void start() {
                    thrCount = threadLocal(count);
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

