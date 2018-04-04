//******************************************************************************
//
// File:    ModelListener.java
// Package: ---
// Unit:    Interface ModelListener
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
 * Interface ModelListener specifies the interface for an object that receives
 * reports from the Model in the Tic-Tac-Toe Game.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public interface ModelListener
	{

// Exported operations.

	/**
	 * Report that a new game was started.
	 */
	public void newGame();


	/**
	 * 
	 * Report that the queen was set
	 * 
	 */
	public void setQueen(int i, int j, Mark mark);

	/**
	 * 
	 * Report that a cell was set visible
	 * 
	 */
	public void setVisible(int i, int j, boolean v);


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
	public void otherTurn
		(String name);

	/**
	 * Report that the player wins.
	 */
	public void youWin();

	/**
	 * Report that the other player wins.
	 *
	 * @param  name  Other player's name.
	 */
	public void otherWin
		(String name);

	/**
	 * Report that a player quit.
	 */
	public void quit();

}

