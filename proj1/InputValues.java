/**
 * A thread class that inputs the initial values derived from the command line 
 *      into the monitor. A runnable object that puts numbers into a HeldValue x 
 *      times.
 * 
 * @author Jahongir Amirkulov
 * @version 02/15/18
 */
public class InputValues implements Runnable{

    private int a, b, c;
    private HeldValue hv;

    /**
     * InputValues Constructor for inputting initial values
     * @param a - coefficient a
     * @param b - coefficient b
     * @param c - coefficient c
     * @param hv - the monitor class
     */
    public InputValues(int a, int b, int c, HeldValue hv) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.hv = hv;
    }

    /**
     * Run method for the thread. It will put the initial values for H, F, and G.
     * 
     *  @exception  InterruptedException
     *     Thrown if the calling thread is interrupted while running the run 
     *     method.
     */
    public void run() {
        try {
            // put C to F
            hv.putF(0, c);
            //adding a + b and then passing it to putG
            hv.putG(0, (a + b));
            // put 2 * a to H
            hv.putH(2 * a);
        } catch (InterruptedException exc) {

        }   
    }
}