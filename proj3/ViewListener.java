//******************************************************************************
//
// File:    ViewListener.java
// Package: ---
// Unit:    Interface ViewListener
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
 * Interface ViewListener specifies the interface for an object that receives
 * reports from the View in the Tic-Tac-Toe Game.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public interface ViewListener
	{

// Exported operations.

	/**
	 * Report that a player joined the game.
	 *
	 * @param  view  View object making the report.
	 * @param  name  Player's name.
	 */
	public void join
		(ModelListener view,
		 String name);

	/**
	 * Report that a square was chosen.
	 *
	 * @param  view  View object making the report.
	 * @param  i     Square index.
	 */
	public void squareChosen
		(ModelListener view,
		 int i, int j);

	/**
	 * Report that a new game was requested.
	 *
	 * @param  view  View object making the report.
	 */
	public void newGame
		(ModelListener view);

	/**
	 * Report that the player quit.
	 *
	 * @param  view  View object making the report.
	 */
	public void quit
		(ModelListener view);

	}

