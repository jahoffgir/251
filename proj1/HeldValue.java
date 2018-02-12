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

    /**
     * Default Constructor 
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
    public void putF(int x, int value) {

    }

    /**
     * puts the given value into the monitor as the value for G(x). It is 
     * an error if x is not in the range 0 through max inclusive. It is 
     * an error if the value of G(x) has already been put.
     * 
     * @param int x - location of the value
     * @param int value - value to be stored
     */
    public void putG(int x, int value) {

    }

    /**
     * puts the given value into the monitor as the value for G(x). It is 
     * an error if x is not in the range 0 through max inclusive. It is 
     * an error if the value of G(x) has already been put.
     * 
     * @param int x - location of the value
     * @param int value - value to be stored
     */
    public void putH(int x, int value) {
    
    }
    /**
     * 
     * returns the value of F(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of F(x) has been put.
     * 
     * @param int x 
     */
    public int getF(int x) {
        return 0;
    }

    /**
     * 
     * returns the value of G(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of G(x) has been put.
     * 
     * @param int x 
     */
    public int getG(int x) {
        return 0;
    }

    /**
     * 
     * returns the value of H(x) stored in the monitor. It is an error if x
     * is not in the range 0 through max inclusive. This method does not 
     * return until the value of H(x) has been put.
     * 
     * @param int x 
     */
    public int getH(int x) {
        return 0;
    }


}
