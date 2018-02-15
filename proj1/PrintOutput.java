/**
 * A thread class that prints the program's output. There is one instance of 
 *      this class.
 * 
 * @author Jahongit Amirkulov
 * @version 02/12/18
 */
public class PrintOutput implements Runnable{
    
    private HeldValue hv;
    private int max;
    /**
     * PrintOut Constructor 
     */
    public PrintOutput(HeldValue hv, int max) {
        this.hv = hv;
        this.max = max;
    }

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
            // Shouldn't happen.
        }
    }
}