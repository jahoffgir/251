/**
 * A Goldbach number is an even number that is the sum of two primes. The
 * Goldbach Conjecture, posed by German mathematician Christian Goldbach in 1742
 * and as yet not proven or disproven, is that every even number greater than 4 
 * is a Goldbach number.
 *
 * @author Jahongir Amirkulov
 * @version 03/01/18
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
    IntVbl min;
    IntVbl max;

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
            int N = Integer.parseInt(args[0]);
            // checking for even number
            if (N % 2 != 0) { 
                System.err.println("<n> must be an even number > 4");
                System.exit(1);
            }
            num = new IntVbl.Sum(0);
            min = new IntVbl.Min(N);
            max = new IntVbl.Max(3);
            
            parallelFor (3, N/2) .exec (new Loop() {
                IntVbl thrCount;
                IntVbl thrMin;
                IntVbl thrMax;

                /**
                 * Start method
                 */
                public void start() {
                    thrCount = threadLocal(num);
                    thrMin = threadLocal(min);
                    thrMax = threadLocal(max);
                 }

                /**
                 * Run method
                 * @param i - index 
                 */
                public void run (int i) {
                    // BigInteger for i
                    BigInteger bigi = BigInteger.valueOf(i);
                    // checking if i is prime
                    if (bigi.isProbablePrime(64)) {
                        // the other half, y
                        int y = N - i;
                        BigInteger bigj = BigInteger.valueOf(y);
                        // check y is prime
                        if (bigj.isProbablePrime(64)) {
                            // add the min
                            if (thrMin.item > i) thrMin.item = i;
                            // add the max
                            if (thrMax.item < i) thrMax.item = i;
                            // increment the count of the solution
                            thrCount.item++;
                        }
                    }                                        
                }
            });
            
            // printing the statement
            if (num.item == 0) System.out.println("No solutions");
            else if (num.item == 1) {
                System.out.printf("%d solution\n", num.item);
                System.out.printf("%d = %d + %d\n", N, min.item, N - min.item);
            } else {
                System.out.printf("%d solutions\n", num.item);
                System.out.printf("%d = %d + %d\n", N, min.item, N - min.item);
                System.out.printf("%d = %d + %d\n", N, max.item, N - max.item);
            }
        } 
        // Checking the type of the args
        catch (IllegalArgumentException e) {
            System.err.println("Argument is not an int. Exiting.");
            System.exit(1);
        }
    }
}
