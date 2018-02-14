import java.util.ArrayList;
/**
 *
 * A monitor class that holds the value of F(x) for x from 0 to max inclusive,
 * the value of G(x) for x from 0 to max inclusive, and the value of H. There
 * is one instance of this class.
 *
 * @author  Jahongir Amirkulov
 * @version 02/12/18
 * 
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
    private int hValue;
    private boolean valuePresent = false;
    
    // max value of x
    private int max;
    
    /**
     * 
     * Constructor that initializes the Arrays
     *
     */
    public HeldValue(int max) {
        gArray = new int[max + 1];
        fArray = new int[max + 1];
        gBool = new boolean[max + 1];
        fBool = new boolean[max + 1];
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
    public synchronized void putF(int x, int value) throws InterruptedException{
        // error if the x is out of bounds. From 0 to max, inclusive.
        if (x < 0 && x > max) throw new IllegalArgumentException();
        // error when value has already been put
        else if (fBool[x]) throw new IllegalStateException();

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
    public synchronized void putG(int x, int value) throws InterruptedException{
        // error if the x is out of bounds. From 0 to max, inclusive.
        if (x < 0 && x > max) throw new IllegalArgumentException();
        // error when value has already been put
        // else if (fBool[x]) throw new IllegalStateException();

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
    public synchronized void putH(int value) throws InterruptedException{
        // error when value has already been put
        if (hBool) throw new IllegalStateException();
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
    public synchronized int getF(int x) throws InterruptedException{
        // error if the x is out of bounds. From 0 to max, inclusive.
        try {
            while (!fBool[x]) {
                wait();
            }
    
        } catch (IllegalArgumentException e) {}
        
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
    public synchronized int getG(int x) throws InterruptedException{
        try {
            while (!gBool[x]) {
                wait();
            }
        
        } catch (IllegalArgumentException e) {}
        return gArray[x];
    }

    /**
     * 
     * returns the value of H(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of H(x) has been put.
     * 
     */
    public synchronized int getH() throws InterruptedException{
        while (!hBool) {
            wait();
        }
        
        return hValue;
    }
}
