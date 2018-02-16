

/**
 *  Calculate a quadratic function, F(x) = ax2 + bx + c, given the fixed 
 *      coefficients a, b, and c, for integer values of x: F(0), F(1), F(2), and so on..
 * 
 * Usage: java Quadratic <a> <b> <c> <max>
 * @author  Jahongir Amirkulov
 * @version 02/15/18
 */
public class Quadratic {

    /**
     * Choice enum for deciding which addition to do.
     */
    public static enum Choice {
        ADDTOF, ADDTOG    
    }
    
    /**
     * Main method
     */
    public static void main(String [] args) {
        // Checking the number of args
        if (args.length != 4) {
            System.err.println("Incorrect number of arguments. Need four arguments.");
            System.exit(1);
        }
        // input variables
        int a;
        int b;
        int c;
        int max;
        try {
            // coefficients of a, b, c, and max
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            c = Integer.parseInt(args[2]);
            max = Integer.parseInt(args[3]);

            // Checking the value of max
            if (max < 0) {
                System.err.println("Maximum value of x is NOT greater than or equal to 0.");
                System.exit(1);
            } 
            // Monitor class
            HeldValue hv = new HeldValue(max);
            // Choice enum - add to G first
            Choice choice = Choice.ADDTOG;
            // Starting PrintOut thread
            new Thread (new PrintOutput(hv, max)).start();

            for (int i = 0; i < max; i++) {
                // Starting AdditionOperator
                new Thread (new AdditionOperator(hv, choice, i)).start();
            }
            // Change choice to F
            choice = Choice.ADDTOF;
            for (int i = 0; i < max; i++) {
                //Starting AdditionOperator 
                new Thread (new AdditionOperator(hv, choice, i)).start();
            }
            // Starting InputValues
            new Thread (new InputValues(a, b, c, hv)) .start();  
        }
        // Checking the type of the args
        catch (IllegalArgumentException e) {
            System.err.println("Arguments are not integers. Exiting.");
            System.exit(1);
        }
    }
}
