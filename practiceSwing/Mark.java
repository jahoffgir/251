//******************************************************************************
//
// File:    Mark.java
// Package: ---
// Unit:    Enum Mark
//
// This Java source file is copyright (C) 2018 by Alan Kaminsky. All rights
// reserved. For further information, contact the author, Alan Kaminsky, at
// ark@cs.rit.edu.
//
// This Java source file is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by the Free
// Software Foundation; either version 3 of the License, or (at your option) any
// later version.
//
// This Java source file is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// details.
//
// You may obtain a copy of the GNU General Public License on the World Wide Web
// at http://www.gnu.org/licenses/gpl.html.
//
//******************************************************************************

/**
 * Enum Mark enumerates the marks that can appear on a Tic-Tac-Toe game board.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public enum Mark
	{
	/**
	 * Square is blank.
	 */
	BLANK,

	/**
	 * Square is marked X.
	 */
	X,

	/**
	 * Square is marked O.
	 */
	O;

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
	public static Mark valueOf
		(int ordinal)
		{
		switch (ordinal)
			{
			case 0: return BLANK;
			case 1: return X;
			case 2: return O;
			default:
				throw new IllegalArgumentException (String.format
					("Mark.valueOf(): ordinal = %d illegal", ordinal));
			}
		}

	// Note: Use the <TT>ordinal()</TT> method to convert an enumeral to an
	// ordinal.

	}
