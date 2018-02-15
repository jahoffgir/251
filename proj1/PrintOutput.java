/**
 * A thread class that prints the program's output iteratively as it is being
 * computed.
 * 
 * @author Jahongit Amirkulov
 * @version 02/14/18
 */
public class PrintOutput implements Runnable{
    
    private HeldValue hv;
    private int max;
    /**
     * Constructor for PrintOutput Constructor
     * @param HeldValue hv - HeldValue monitor class
     * @param int max - max value of the number of times to iterate 
     */
    public PrintOutput(HeldValue hv, int max) {
        this.hv = hv;
        this.max = max;
    }

    /**
     * * Run method for the thread. It will print the values of i, h, g and f.
     * 
     *  @exception  InterruptedException
     *     Thrown if the calling thread is interrupted while running the run 
     *     method.
     */
    public void run() {
        try {  
            for (int i = 0; i <= max; ++ i) {
                System.out.print(i + "\t");
                System.out.print(hv.getH() + "\t");
                System.out.print(hv.getG(i) + "\t");
                System.out.println(hv.getF(i));
            }   
        }
        catch (InterruptedException exc) {
            throw new InterruptedException();
        }
    }
}