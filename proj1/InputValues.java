/**
 * A thread class that inputs the initial values derived from the command line 
 *      into the monitor. A runnable object that puts numbers into a HeldValue x 
 *      times.
 * 
 * @author Jahongir Amirkulov
 * @version 02/12/18
 * TODO Finish the documentation
 */
public class InputValues implements Runnable{

    private int a, b, c;
    private HeldValue hv;

    /**
     * Default Constructor
     */
    public InputValues(int a, int b, int c, HeldValue hv) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.hv = hv;
    }

    public void run() {
        try {
            hv.putF(0, c);
            //adding a + b and then passing it to putG
            hv.putG(0, (a + b));

            hv.putH(2 * a);
        } catch (InterruptedException exc) {}   
    }
}