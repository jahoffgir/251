import java.util.Arrays;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/**
 * Class BoardState encapsulates the state of a SixQueen game board.
 *
 * @author  Alan Kaminsky
 * @author  Jahongir Amirkulov
 * @version 04/05/18
 */
public class BoardState {

	// Exported data members.

	/**
	 * Number of squares on the board.
	 */
	public static final int N_SQUARES = 6;

	// Hidden data members.
	
	private Mark[][] mark;    // Mark on each square

	// Exported constructors.

	/**
	 * Construct a new board state object.
	 */
	public BoardState() {
		mark = new Mark[N_SQUARES][N_SQUARES];
		clear();
	}

	// Exported operations.

	/**
	 * Clear this board state object.
	 */
	public void clear() {
		for (int i = 0; i < N_SQUARES; i++) {
			Arrays.fill (mark[i], Mark.BLANK);
		}	
	}

	/**
	 * 
	 * Sets the queen on the board
	 *
	 * @param i 	row
	 * @param j 	col
	 * @param mark mark
	 */
	public void setQueen(int i, int j, Mark mark) {
		setMark(i, j, mark);
		setVisible(i, j, Mark.I);
	}

	/**
	 * 
	 * Helper for setvisible
	 * 
	 * @param i 	row
	 * @param j 	col
	 * @param mark 	mark
	 */
	private void setVisibleHelper(int i, int j, Mark mark) {
		int sI = i;
		int sJ = j;
		int aI = i;
		int aJ = j;
		// sets the diagonal cells invisible
		for (int a = 0; a < i; a++) {
			sI--;
			sJ--;
			aI--;
			aJ++;
			if (sI >= 0 && sJ >= 0)
				if (this.mark[sI][sJ] != Mark.Q)
					this.mark[sI][sJ] = mark;
			if (aI >= 0 && aJ < BoardState.N_SQUARES)
				if (this.mark[aI][aJ] != Mark.Q)
					this.mark[aI][aJ] = mark;
		}
		sI = i;
		sJ = j;
		aI = i;
		aJ = j;
		for (int a = 0; a < (BoardState.N_SQUARES - i) ; a++) {
			sI++;
			sJ++;
			aI++;
			aJ--;
			if (sI < BoardState.N_SQUARES && sJ < BoardState.N_SQUARES)
				if (this.mark[sI][sJ] != Mark.Q)
					this.mark[sI][sJ] = mark;
			if (aJ >= 0 && aI < BoardState.N_SQUARES)
				if (this.mark[aI][aJ] != Mark.Q)
					this.mark[aI][aJ] = mark;
		}
	}

	/**
	 * Sets the cells that are vertical, horizontal, and diagonal invisible
	 * @param i 	row
	 * @param j 	col
	 * @param mark 	mark
	 */
	public void setVisible(int i, int j, Mark mark) {
		// set the horizontal and vertical cells invisible
		for (int x = 0; x < N_SQUARES; x++) {
			if (x != i)
				this.mark[x][j] = mark;
		}	
		for (int x = 0; x < N_SQUARES; x++) {
			if (x != j)
				this.mark[i][x] = mark;
		}
		this.setVisibleHelper(i, j, mark);
	}

	/**
	 * Set the mark on the given square.
	 *
	 * @param  i     Square index.
	 * @param  j 	 Square index.
	 * @param  mark  Mark.
	 */
	public void setMark(int i, int j, Mark mark) {
		this.mark[i][j] = mark;
	}

	/**
	 * Get the mark on the given square.
	 *
	 * @param  i  Square index.
	 * @param  j  Square index.
	 *
	 * @return  Mark.
	 */
	public Mark getMark(int i, int j) {
		return this.mark[i][j];
	}

	/**
	 * Determine if the marks on the board are a winning combination for the
	 * given player.
	 *
	 *
	 * @return  win or not
	 */
	public int checkWin() {
		for (int i = 0; i < N_SQUARES; i++)
			for (int j = 0; j < N_SQUARES; j++) {
				if (mark[i][j] == Mark.BLANK)
					return -1;
			}
		return 1;
	}
}