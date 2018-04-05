/**
 * Interface ModelListener specifies the interface for an object that receives
 * reports from the Model in the SixQueen Game.
 *
 * @author  Alan Kaminsky
 * @author  Jahongir Amirkulov
 * @version 04/05/18
 */
public interface ModelListener {

	// Exported operations.
	/**
	 * Report that a new game was started.
	 */
	public void newGame();

	/**
	 * 
	 * Report that the queen was set
	 * 
	 * @param i row
	 * @param j col
	 */
	public void setQueen(int i, int j);

	/**
	 * 
	 * Report that a cell was set visible
	 * 
	 * @param i row
	 * @param j col
	 */
	public void setVisible(int i, int j);

	/**
	 * Report that the player is waiting for a partner.
	 */
	public void waitingForPartner();

	/**
	 * Report that it's the player's turn.
	 */
	public void yourTurn();

	/**
	 * Report that it's the other player's turn.
	 *
	 * @param  name  Other player's name.
	 */
	public void otherTurn(String name);

	/**
	 * Report that the player wins.
	 */
	public void youWin();

	/**
	 * Report that the other player wins.
	 *
	 * @param  name  Other player's name.
	 */
	public void otherWin(String name);

	/**
	 * Report that a player quit.
	 */
	public void quit();
}