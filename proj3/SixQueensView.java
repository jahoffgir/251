import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * Class SixQueensView provides the user interface for the Six Queens Game.
 *
 * @author  Alan Kaminsky
 * @author Jahongir Amirkulov
 * @version 03/26/2018
 */
public class SixQueensView implements ModelListener {

    // Hidden data members.

    private static final int GAP = 10;

    private JFrame frame;
    private SixQueensJPanel board;
    private JTextField messageField;
    private JButton newGameButton;

    private ViewListener listener;

    // Hidden constructors.

	/**
	 * Construct a new Six Queens view object.
	 *
	 * @param  name  Player's name.
	 */
	private SixQueensView(String name) {
		frame = new JFrame ("Six Queens -- " + name);
		JPanel p1 = new JPanel();
		p1.setLayout (new BoxLayout (p1, BoxLayout.Y_AXIS));
		p1.setBorder (BorderFactory.createEmptyBorder (GAP, GAP, GAP, GAP));
		frame.add (p1);

		board = new SixQueensJPanel();
		board.setFocusable (false);
		board.setAlignmentX (0.5f);
		p1.add (board);
		p1.add (Box.createVerticalStrut (GAP));
		messageField = new JTextField (5);
		messageField.setEditable (false);
		messageField.setFocusable (false);
		messageField.setAlignmentX (0.5f);
		p1.add (messageField);
		p1.add (Box.createVerticalStrut (GAP));
		newGameButton = new JButton ("New Game");
		newGameButton.setFocusable (false);
		newGameButton.setAlignmentX (0.5f);
		p1.add (newGameButton);
		frame.pack();
		frame.setVisible (true);
	}
    /**
     * Construct a new Tic-Tac-Toe view object.
    *
    * @param  name  Player's name.
    *
    * @return  View object.
    */
    public static SixQueensView create(String name) {
        UIRef uiref = new UIRef();
        onSwingThreadDo (new Runnable() {
            public void run() {
                uiref.ui = new SixQueensView (name);
            }
        });
        return uiref.ui;
    }
 
    private static class UIRef {
        public SixQueensView ui;
    }

    /**
     * Set the view listener.
     *
     * @param  listener  View listener.
     */
    public void setListener(ViewListener listener) {
        onSwingThreadDo (new Runnable() {
            public void run() {
                SixQueensView.this.listener = listener;
            }
        });
    }

    /**
     * Report that a new game was started.
     */
    public void newGame() {
        onSwingThreadDo (new Runnable() {
            public void run() {
                board.clear();
            }
        });
    }
    /**
     * Report that a mark was placed on a square.
    *
    * @param  i     Square index.
    * @param  mark  Mark.
    */
    public void setMark(int i, Mark mark) {
        onSwingThreadDo (new Runnable() {
            public void run() {
                board.setMark (i, mark);
            }
        });
    }
 
    /**
     * Report a winning combination.
    *
    * @param  i  Winning combination number.
    */
    public void setWin(int i) {
        onSwingThreadDo (new Runnable()
            {
            public void run()
                {
                board.setWin (i);
                }
            });
    }

    /**
     * Report that the player is waiting for a partner.
    */
    public void waitingForPartner() {
        onSwingThreadDo (new Runnable() {
            public void run() {
                messageField.setText ("Waiting for partner");
                newGameButton.setEnabled (false);
            }
        });
    }

    /**
     * Report that it's the player's turn.
    */
    public void yourTurn() {
        onSwingThreadDo (new Runnable() {
            public void run() {
                messageField.setText ("Your turn");
                newGameButton.setEnabled (true);
            }
        });
    }
    /**
     * Report that it's the other player's turn.
    *
    * @param  name  Other player's name.
    */
    public void otherTurn (String name) {
        onSwingThreadDo (new Runnable() {
            public void run() {
                messageField.setText (name + "'s turn");
                newGameButton.setEnabled (true);
            }
        });
    }
 
    /**
     * Report that the player wins.
     */
    public void youWin() { 
    onSwingThreadDo (new Runnable() {
            public void run() {
                messageField.setText ("You win!");
                newGameButton.setEnabled (true);
            }
        });
    }

    /**
     * Report that the other player wins.
    *
    * @param  name  Other player's name.
    */
    public void otherWin (String name) {
        onSwingThreadDo (new Runnable() {
            public void run() {
                messageField.setText (name + " wins!");
                newGameButton.setEnabled (true);
                }
        });
    }

     /**
      * Report that the game is a draw.
      */
    public void draw() {
        onSwingThreadDo (new Runnable() {
             public void run() {
                 messageField.setText ("Draw");
                 newGameButton.setEnabled (true);
                 }
             });
        }

    /**
     * Report that a player quit.
     */
    public void quit() {
        System.exit (0);
    }

 // Hidden operations.

    /**
     * Execute the given runnable object on the Swing thread.
    */
    private static void onSwingThreadDo(Runnable task) {
        try {
            SwingUtilities.invokeAndWait (task);
        } catch (Throwable exc) {
            exc.printStackTrace (System.err);
            System.exit (1);
        }
    }
}
