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

    // Array representing values of G and F
    private int[] gArray; 
    private int[] fArray;

    // Array representing bool values of G and F
    private boolean[] gBool; 
    private boolean[] fBool;
    private boolean hBool = false;

    // int repesenting value of H
    private int hValue = null;
    private boolean valuePresent = false;
    
    private int max;
    /**
     * 
     * Constructor that initializes the Arrays
     *
     */
    public HeldValue(int max) {
        gArray = new int[max];
        fArray = new int[max];
        gBool = new boolean[max];
        fBool = new boolean[max];
        this.max = max;
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
        // error when value has already been put
        if (fArray[x]) throw (IllegalStateException);
        // insert a value to an array
        fArray[x] = value;
        fBool[x] = true;
        // insert a bool value to an array
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
    public synchronized void putG(int x, int value) {
        // error when value has already been put
        if (fArray[x]) throw (IllegalStateException);
        // insert a value to an array
        gArray[x] = value;
        gBool[x] = true;
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
        // error when value has already been put
        if (hBool) throw (IllegalStateException);
        // insert a value to an hValue
        hValue = value;
        hBool = true;
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
        if (x < 0 && x > max) throw IllegalArgumentException;
        while (!fBool[x]) {
            wait();
        }
        notifyAll();
        return fArray[x];
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
        // Error, x is not in range from inclusive 0 to max.
        if (x < 0 && x > max) throw IllegalArgumentException;
        while (!gBool[x]) {
            wait();
        }
        notifyAll();
        return gArray[x];
    }

    /**
     * 
     * returns the value of H(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of H(x) has been put.
     * 
     */
    public synchronized int getH() {
        while (!hBool) {
            wait();
        }
        notifyAll();
        return hValue;
    }


}
