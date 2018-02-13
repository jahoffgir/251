/**
 * 
 * A thread class that performs one and only one of the addition operations 
 *      shown in the above diagrams, computing either one of the F values or 
 *      one of the G values. 
 * 
 * @author Jahongir Amirkulov
 * @version 02/12/18
 * 
 */
 public class AdditionOperator implements Runnable{

    private HeldValue hv;
    private int max;

    /**
     * Default Constructor
     */
    public AdditionOperator(HeldValue hv, int max) {
        this.max = max;
        this.hv = hv;
    }

    public void run() {
        try {  
            for (int i = 0; i < max; ++ i) {
                hv.putG(i + 1, hv.getG(i) + hv.getH());
                hv.putF(i + 1, hv.getF(i)+ hv.getG(i));
            }   
        }
        catch (InterruptedException exc) {
            // Shouldn't happen.
        }
    }

 }