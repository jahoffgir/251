/**
 * A Goldbach number is an even number that is the sum of two primes. The
 * Goldbach Conjecture, posed by German mathematician Christian Goldbach in 1742
 * and as yet not proven or disproven, is that every even number greater than 4 
 * is a Goldbach number.
 *
 * @author Jahongir Amirkulov
 * @version 02/26/18
 */
import edu.rit.pj2.Loop;
import edu.rit.pj2.IntParallelForLoop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.IntArrayVbl;
import java.math.BigInteger;
public class Goldbach extends Task{
  
    // Stores all of the prime numbers that equal to the input
    IntArrayVbl arr;

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
            arr = new IntArrayVbl(n);        
            parallelFor (1, n) .exec (new Loop() {
                IntArrayVbl thrCount;
                
                /**
                 * Start method
                 */
                public void start() {
                    thrCount = threadLocal(arr);
                }

                /**
                 * Run method
                 * @param i 
                 */
                public void run (int i) {
                    // convert int to BigInteger
                    BigInteger bigi = BigInteger.valueOf(i);

                    // checking if it is a prime
                    if (bigi.isProbablePrime(64)) {

                        // checking if the next nums are prime that equal n
                        for (int j = i; i < n; j++) {
                            BigInteger bigj = BigInteger.valueOf(j);       
                            if (bigi.isProbablePrime(64)) {
                                // step 1 check if i and j equal to n
                                if (i + j == n) {                   
                                    // step 2 store that num to the array    
                                    thrCount.item[i-1] = i;
                                }                            
                            }  
                        }
                    }
                }
            });
            for (int i = 0; i < arr.item.length; i++) {
                System.out.println( arr.item[i]);
                
            }   
        } 
        // Checking the type of the args
        catch (IllegalArgumentException e) {
            System.err.println("Argument is not an int. Exiting.");
            System.exit(1);
        }
    }
}
