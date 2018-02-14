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
    private Quadratic.Choice choice;
    private int idx;
    /**
     * Default Constructor
     */
    public AdditionOperator(HeldValue hv, int max, Quadratic.Choice choice, int idx) {
        this.max = max;
        this.hv = hv;
        this.choice = choice;
        this.idx = idx;
    }

    public void run() {
        try {  
            switch (choice) {
                case ADDTOF:
                    hv.putF(idx + 1, hv.getF(idx)+ hv.getG(idx));
                    break;
                        
                case ADDTOG:
                    hv.putG(idx + 1, hv.getG(idx) + hv.getH());
                    break;
            }
            // for (int i = 0; i < max; ++ i) {
            //     hv.putG(i + 1, hv.getG(i) + hv.getH());
            //     hv.putF(i + 1, hv.getF(i)+ hv.getG(i));
            // }   
        }
        catch (InterruptedException exc) {
            // Shouldn't happen.
        }
    }

 }