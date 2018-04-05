/**
 * Enum Mark enumerates the marks that can appear on a SixQueen game board.
 *
 * @author  Alan Kaminsky
 * @author  Jahongir Amirkulov
 * @version 04/05/18 
 */
public enum Mark{

	/**
	 * Square is Blank.
	 */
	BLANK,

	/**
	 * Square is marked with Queen.
	 */
	Q,

	/**
	 * Square is marked Invisible.
	 */
	I;

	/**
	 * Convert the given ordinal to an enumeral.
	 *
	 * @param  ordinal  Ordinal.
	 *
	 * @return  Enumeral.
	 *
	 * @exception  IllegalArgumentException
	 *     (unchecked exception) Thrown if <TT>ordinal</TT> is invalid.
	 */
	public static Mark valueOf(int ordinal) {
		switch (ordinal) {
			case 0: return BLANK;
			case 1: return Q;
			case 2: return I;
			default:
				throw new IllegalArgumentException (String.format
					("Mark.valueOf(): ordinal = %d illegal", ordinal));
		}
	}
}

