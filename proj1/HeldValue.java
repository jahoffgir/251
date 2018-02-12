import java.util.ArrayList;
/**
 *
 * A monitor class that holds the value of F(x) for x from 0 to max inclusive,
 * the value of G(x) for x from 0 to max inclusive, and the value of H. There
 * is one instance of this class.
 *
 * @author  Jahongir Amirkulov
 * @version 02/12/18
 */

/**
 * A monitor class providing a held value.
 */
public class HeldValue {

    private int x;
    private int value;
    private ArrayList<Integer> gArray = new ArrayList<>();
    private ArrayList<Integer> fArray = new ArrayList<>();
    private int hValue = null;
    private boolean valuePresent = false;
    /**
     * 
     * Constructor 
     *
     */
    public HeldValue() {
        
    }

    /**
     * puts the given value into the monitor as the value for F(x). It is 
     * an error if x is not in the range 0 through max inclusive. It is 
     * an error if the value of F(x) has already been put.
     * 
     * @param int x - location of the value
     * @param int value - value to be stored
     * 
     */
    public synchronized void putF(int x, int value) {
        if (fArray.get(x)) {
            // THROW AN ERROR
        }
        fArray.add(x, value);
        notifyAll();
    }

    /**
     * puts the given value into the monitor as the value for G(x). It is 
     * an error if x is not in the range 0 through max inclusive. It is 
     * an error if the value of G(x) has already been put.
     * 
     * @param int x - location of the value
     * @param int value - value to be stored
     */
    public synchronized void putG(int x, int value) {
        if (gArray.get(x)) {
            // THROW AN ERROR
        }
        gArray.add(x, value);
        notifyAll();
    }

    /**
     * puts the given value into the monitor as the value for G(x). It is 
     * an error if x is not in the range 0 through max inclusive. It is 
     * an error if the value of G(x) has already been put.
     * 
     * @param int x - location of the value
     * @param int value - value to be stored
     * 
     */
    public synchronized void putH(int value) {
        if (hValue) {
            // THROW AN ERROR
        }
        hValue = value;
        notifyAll();
    }
    /**
     * 
     * returns the value of F(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of F(x) has been put.
     * 
     * @param int x 
     */
    public synchronized int getF(int x) {
        valuePresent = false;
        // Condition: Value is present.
        if (gArray.get(x))
            valuePresent = true;
        
        while (! valuePresent) {
            // Condition: Value is present.
            if (gArray.get(x))
                valuePresent = true;
            wait();
        }
        notifyAll();
        return gArray.get(x);
    }

    /**
     * 
     * returns the value of G(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of G(x) has been put.
     * 
     * @param int x 
     */
    public synchronized int getG(int x) {
        valuePresent = false;
        if (gArray.get(x))
            valuePresent = true;
        while (! valuePresent) {
            // Condition: Value is present.
            if (gArray.get(x))
                valuePresent = true;
            wait();
        }
        notifyAll();
        return gArray.get(x);
    }

    /**
     * 
     * returns the value of H(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of H(x) has been put.
     * 
     * @param int x 
     */
    public synchronized int getH(int x) {
        valuePresent = false;
        if (hValue)
            valuePresent = true;
        
        while (! valuePresent) {
            // Condition: Value is present.
            if (gArray.get(x))
                valuePresent = true;
            wait();
        }
        notifyAll();
        return hValue;
    }


}
