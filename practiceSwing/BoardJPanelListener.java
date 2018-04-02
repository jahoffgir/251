//******************************************************************************
//
// File:    BoardJPanelListener.java
// Package: ---
// Unit:    Class BoardJPanelListener
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
 * Interface BoardJPanelListener specifies the interface for an object that
 * listens for clicks on a {@linkplain BoardJPanel}.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public interface BoardJPanelListener
	{

// Exported operations.

	/**
	 * Report that the given board square was clicked.
	 *
	 * @param  i  Square index.
	 */
	public void squareClicked
		(int i);

	}
