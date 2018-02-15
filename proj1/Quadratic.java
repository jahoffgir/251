

/**
 * Class AncientPrinter provides an object that simulates an ancient printer.
 *      The ancient printer displays bytes at the rate of 30 bytes per second, like
 *      the early hardcopy terminals that connected to the computer using a 300-bps
 *      modem (10 bits/character). The printer output is displayed in its own window
 *      on the screen.
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
    
    public static void main(String [] args) {
        // Checking the number of args
        if (args.length != 4) {
            System.err.println("Incorrect number of arguments. Need four arguments.");
            System.exit(1);
        }
        
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
            HeldValue hv = new HeldValue(max);
            
            Choice choice = Choice.ADDTOG;
            

            new Thread (new PrintOutput(hv, max)).start();

            for (int i = 0; i < max; i++) {
                
                new Thread (new AdditionOperator(hv, max, choice, i)).start();
            }
            choice = Choice.ADDTOF;
            for (int i = 0; i < max; i++) {
                
                new Thread (new AdditionOperator(hv choice, i)).start();
            }
            
            
            new Thread (new InputValues(a, b, c, hv)) .start();
            
        }
        // Checking the type of the args
        catch (IllegalArgumentException e) {
            System.err.println("Arguments are not integers. Exiting.");
            System.exit(1);
        }
        
    }
}
