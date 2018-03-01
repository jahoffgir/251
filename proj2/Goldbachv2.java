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
import edu.rit.pj2.vbl.IntVbl;
import java.math.BigInteger;
public class Goldbach extends Task{
  
    // Stores all of the prime numbers that equal to the input
    IntVbl num;
    

    
    /**
     * Main Program
     */
    public void main(String [] args) throws Exception {
        // Validate command line arguments.
        if (args.length < 1) {
            System.err.println("Incorrect number of arguements");
            System.exit(1);
        }

//        try {
            // even number n
            int N = Integer.parseInt(args[0]);
            // checking for even number
            if (N % 2 != 0) { 
                System.err.println("<n> must be an even number > 4");
                System.exit(1);
            }
            int[] arrs = new int[N];
            num = new IntVbl.Sum();
            num.item = 0;
            parallelFor (0, N-1) .exec (new Loop() {
                IntVbl thrCount;
                
                /**
                 * Start method
                 */
                public void start() {
                    thrCount = threadLocal(num);
                }

                /**
                 * Run method
                 * @param i 
                 */
                public void run (int i) {
                    i++;
                    // convert int to BigInteger
                    BigInteger bigi = BigInteger.valueOf(i);

                    // checking if it is a prime
                    if (bigi.isProbablePrime(64)) {

                        // checking if the next nums are prime that equal n
                        for (int j = i; i < N; j++) {
                            BigInteger bigj = BigInteger.valueOf(j);       
                            if (bigi.isProbablePrime(64)) {
                                // step 1 check if i and j equal to n
                                if (i + j == N) {                   
                                    // step 2 store that num to the array    
                                    arrs[i-1] = i;
                                }                            
                            }  
                        }
                    }
                }
            });
            for (int i = 0; i < arrs.length; i++) {
                System.out.println( arrs[i]);
                
            }   
        } 
        // Checking the type of the args
  //      catch (IllegalArgumentException e) {
    //        System.err.println("Argument is not an int. Exiting.");
      //      System.exit(1);
//        }
    //}
}
