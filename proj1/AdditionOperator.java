/**
 * 
 * A thread class that performs one and only one of the addition operations 
 *      shown in the above diagrams, computing either one of the F values or 
 *      one of the G values. 
 * 
 * @author Jahongir Amirkulov
 * @version 02/15/18
 * 
 */
 public class AdditionOperator implements Runnable{

    // the monitor object
    private HeldValue hv;
    // choice for which operation to do
    private Quadratic.Choice choice;
    // destionation index
    private int idx;

    /**
     * AdditionOperator constructor 
     * @param hv - the Held Value monitor class
     * @param choice - which addition to do. From G or H
     * @param idx - the index of the destionation
     */
    public AdditionOperator(HeldValue hv, Quadratic.Choice choice, int idx) {
        this.hv = hv;
        this.choice = choice;
        this.idx = idx;
    }

    /**
     * Run method for running the code when thread starts. It will add depending 
     * on the choice.
     *
     *  @exception  InterruptedException
     *     Thrown if the calling thread is interrupted while running the run 
     *     method.
     */
    public void run() {
        try {  
            switch (choice) {
                // if choice is to add to F
                case ADDTOF:
                    hv.putF(idx + 1, hv.getF(idx)+ hv.getG(idx));
                    break;
                // if choice is to add to G
                case ADDTOG:
                    hv.putG(idx + 1, hv.getG(idx) + hv.getH());
                    break;
            }
        }
        catch (InterruptedException exc) {
            throw new InterruptedException();
        }
    }
 }