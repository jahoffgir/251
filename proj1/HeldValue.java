/**
 *
 * A monitor class that holds the value of F(x) for x from 0 to max inclusive,
 * the value of G(x) for x from 0 to max inclusive, and the value of H. There
 * is one instance of this class.
 *
 * @author  Jahongir Amirkulov
 * @version 02/15/18
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
    // max value of x
    private int max;
    
    /**
     * 
     * Constructor that initializes the Arrays
     *
     */
    public HeldValue(int max) {
        this.max = max;
        this.gArray = new int[max + 1];
        this.fArray = new int[max + 1];
        this.gBool = new boolean[max + 1];
        this.fBool = new boolean[max + 1];
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
        // checking the boundary from 0 to max inclusive
        if (x > max || x < 0) {
            System.err.println("X is not in range 0 through max inclusive");
            System.exit(1);
        }
        // error when value has already been put
        if (fBool[x]) {
            System.err.println("The value of F has already been put.");
            System.exit(1);
        }

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
        // checking the boundary from 0 to max inclusive
        if (x > max || x < 0) {
            System.err.println("X is not in range 0 through max inclusive");
            System.exit(1);
        }
        // error when value has already been put
        if (gBool[x]) {
            System.err.println("The value of G has already been put.");
            System.exit(1);
        }
        // insert a value to an array
        gArray[x] = value;
        gBool[x] = true;
        // insert a bool value to an array
        notifyAll(); 
    }
    

    /**
     * This method puts the given value into the monitor as the value for H. 
     * It is an error if the value of H has already been put.
     *
     * @param int value - value to be stored
     * 
     */
    public synchronized void putH(int value) throws InterruptedException{
        // error when value has already been put
        if (hBool) {
            System.err.println("The value of H has already been put.");
            System.exit(1);
        }
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
     * @return the element that is being accessed
     */
    public synchronized int getF(int x) throws InterruptedException{
        // error if the x is out of bounds. From 0 to max, inclusive.
        if (x > max || x < 0) {
            System.err.println("X is not in range 0 through max inclusive");
            System.exit(1);
        }
        while (!fBool[x]) {
            wait();
        }  
        return fArray[x];
    }

    /**
     * 
     * returns the value of G(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of G(x) has been put.
     * 
     * @param int x the index of the element
     * @return the element that is being accessed
     */
    public synchronized int getG(int x) throws InterruptedException{
        if (x > max || x < 0) {
            System.err.println("X is not in range 0 through max inclusive");
            System.exit(1);
        }
        while (!gBool[x]) {
            wait();
        }
        return gArray[x];
    }

    /**
     * 
     * This method returns the value of H stored in the monitor. This method 
     * does not return until the value of H has been put.
     * 
     * @return hValue - value of H
     */
    public synchronized int getH() throws InterruptedException{
        // waits 
        while (!hBool) {
            wait();
        }
        return hValue;
    }
}
