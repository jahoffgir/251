//******************************************************************************
//
// File:    TicTacToeView.java
// Package: ---
// Unit:    Class TicTacToeView
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class TicTacToeView provides the user interface for the Tic-Tac-Toe Game.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public class TicTacToeView
	implements ModelListener
	{

// Hidden data members.

	private static final int GAP = 10;

	private JFrame frame;
	private BoardJPanel board;
	private JTextField messageField;
	private JButton newGameButton;

	private ViewListener listener;

// Hidden constructors.

	/**
	 * Construct a new Tic-Tac-Toe view object.
	 *
	 * @param  name  Player's name.
	 */
	private TicTacToeView
		(String name)
		{
		frame = new JFrame (name);
		JPanel p1 = new JPanel();
		p1.setLayout (new BoxLayout (p1, BoxLayout.Y_AXIS));
		p1.setBorder (BorderFactory.createEmptyBorder (GAP, GAP, GAP, GAP));
		frame.add (p1);

		board = new BoardJPanel();
		board.setFocusable (false);
		p1.add (board);
		p1.add (Box.createVerticalStrut (GAP));

		JPanel p2 = new JPanel();
		p2.setLayout (new BoxLayout (p2, BoxLayout.X_AXIS));
		p1.add (p2);

		messageField = new JTextField (5);
		messageField.setEditable (false);
		messageField.setFocusable (false);
		p2.add (messageField);
		p2.add (Box.createHorizontalStrut (GAP));

		newGameButton = new JButton ("New Game");
		newGameButton.setFocusable (false);
		p2.add (newGameButton);

		board.setListener (new BoardJPanelListener()
			{
			public void squareClicked (int i)
				{
				if (listener != null)
					listener.squareChosen (TicTacToeView.this, i);
				}
			});
		newGameButton.addActionListener (new ActionListener()
			{
			public void actionPerformed (ActionEvent e)
				{
				if (listener != null)
					listener.newGame (TicTacToeView.this);
				}
			});
		frame.addWindowListener (new WindowAdapter()
			{
			public void windowClosing (WindowEvent e)
				{
				if (listener != null)
					listener.quit (TicTacToeView.this);
				System.exit (0);
				}
			});

		frame.pack();
		frame.setVisible (true);
		}

// Exported operations.

	/**
	 * Construct a new Tic-Tac-Toe view object.
	 *
	 * @param  name  Player's name.
	 *
	 * @return  View object.
	 */
	public static TicTacToeView create
		(String name)
		{
		UIRef uiref = new UIRef();
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				uiref.ui = new TicTacToeView (name);
				}
			});
		return uiref.ui;
		}

	private static class UIRef
		{
		public TicTacToeView ui;
		}

	/**
	 * Set the view listener.
	 *
	 * @param  listener  View listener.
	 */
	public void setListener
		(ViewListener listener)
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				TicTacToeView.this.listener = listener;
				}
			});
		}

	/**
	 * Report that a new game was started.
	 */
	public void newGame()
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
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
	public void setMark
		(int i,
		 Mark mark)
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				board.setMark (i, mark);
				}
			});
		}

	/**
	 * Report a winning combination.
	 *
	 * @param  i  Winning combination number.
	 */
	public void setWin
		(int i)
		{
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
	public void waitingForPartner()
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				messageField.setText ("Waiting for partner");
				newGameButton.setEnabled (false);
				}
			});
		}

	/**
	 * Report that it's the player's turn.
	 */
	public void yourTurn()
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
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
	public void otherTurn
		(String name)
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				messageField.setText (name + "'s turn");
				newGameButton.setEnabled (true);
				}
			});
		}

	/**
	 * Report that the player wins.
	 */
	public void youWin()
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
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
	public void otherWin
		(String name)
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				messageField.setText (name + " wins!");
				newGameButton.setEnabled (true);
				}
			});
		}

	/**
	 * Report that the game is a draw.
	 */
	public void draw()
		{
		onSwingThreadDo (new Runnable()
			{
			public void run()
				{
				messageField.setText ("Draw");
				newGameButton.setEnabled (true);
				}
			});
		}

	/**
	 * Report that a player quit.
	 */
	public void quit()
		{
		System.exit (0);
		}

// Hidden operations.

	/**
	 * Execute the given runnable object on the Swing thread.
	 */
	private static void onSwingThreadDo
		(Runnable task)
		{
		try
			{
			SwingUtilities.invokeAndWait (task);
			}
		catch (Throwable exc)
			{
			exc.printStackTrace (System.err);
			System.exit (1);
			}
		}

	}
