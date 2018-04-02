//******************************************************************************
//
// File:    BoardJPanel.java
// Package: ---
// Unit:    Class BoardJPanel
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Class BoardJPanel provides a Swing widget for displaying a Tic-Tac-Toe game
 * board.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public class BoardJPanel
	extends JPanel
	{

// Hidden data members.

	private static final int L = 1;
	private static final int W = 80;
	private static final int M = 8;

	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color WIN_COLOR = Color.GREEN;
	private static final Color X_COLOR = Color.RED;
	private static final Color O_COLOR = Color.BLUE;

	private static final Stroke LINE_STROKE = new BasicStroke
		(L, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
	private static final Stroke MARK_STROKE = new BasicStroke
		(M, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);

	private BoardState state = new BoardState();
	private BoardJPanelListener listener;

// Exported constructors.

	/**
	 * Construct a new Tic-Tac-Toe game board widget.
	 */
	public BoardJPanel()
		{
		super();
		Dimension d = new Dimension (4*L+3*W, 4*L+3*W);
		setMinimumSize (d);
		setPreferredSize (d);
		setMaximumSize (d);
		addMouseListener (new MouseAdapter()
			{
			public void mouseClicked (MouseEvent e)
				{
				if (listener != null)
					{
					int r = e.getY()/(L + W);
					int c = e.getX()/(L + W);
					listener.squareClicked (3*r + c);
					}
				}
			});
		}

// Exported operations.

	/**
	 * Set this game board widget's listener.
	 *
	 * @param  listener  Board listener.
	 */
	public void setListener
		(BoardJPanelListener listener)
		{
		this.listener = listener;
		}

	/**
	 * Clear this game board widget.
	 */
	public void clear()
		{
		state.clear();
		repaint();
		}

	/**
	 * Set the mark on the given square.
	 *
	 * @param  i     Square index.
	 * @param  mark  Mark.
	 */
	public void setMark
		(int i,
		 Mark mark)
		{
		state.setMark (i, mark);
		repaint();
		}

	/**
	 * Set a winning combination.
	 *
	 * @param  i  Winning combination number.
	 */
	public void setWin
		(int i)
		{
		state.setWin (i);
		repaint();
		}

// Hidden operations.

	/**
	 * Paint this component.
	 *
	 * @param  g  Graphics context.
	 */
	protected void paintComponent
		(Graphics g)
		{
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint
			(RenderingHints.KEY_ANTIALIASING,
			 RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw borders of squares.
		g2d.setColor (LINE_COLOR);
		g2d.setStroke (LINE_STROKE);
		g2d.draw (new Line2D.Float (0,       0,       3*L+3*W, 0      ));
		g2d.draw (new Line2D.Float (0,       L+W,     3*L+3*W, L+W    ));
		g2d.draw (new Line2D.Float (0,       2*L+2*W, 3*L+3*W, 2*L+2*W));
		g2d.draw (new Line2D.Float (0,       3*L+3*W, 3*L+3*W, 3*L+3*W));
		g2d.draw (new Line2D.Float (0,       0,       0,       3*L+3*W));
		g2d.draw (new Line2D.Float (L+W,     0,       L+W,     3*L+3*W));
		g2d.draw (new Line2D.Float (2*L+2*W, 0,       2*L+2*W, 3*L+3*W));
		g2d.draw (new Line2D.Float (3*L+3*W, 0,       3*L+3*W, 3*L+3*W));

		// Draw squares.
		for (int r = 0; r < 3; ++ r)
			for (int c = 0; c < 3; ++ c)
				{
				int i = 3*r + c;

				// Draw background if in a winning combination.
				if (state.getWin (i))
					{
					g2d.setColor (WIN_COLOR);
					g2d.fill (new Rectangle2D.Float
						(L+c*W+c*L, L+r*W+r*L, W, W));
					}

				// Draw an X.
				if (state.getMark (i) == Mark.X)
					{
					g2d.setColor (X_COLOR);
					g2d.setStroke (MARK_STROKE);
					g2d.draw (new Line2D.Float
						(L+c*W+c*L+2*M,   L+r*W+r*L+2*M,
						 L+c*W+c*L+W-2*M, L+r*W+r*L+W-2*M));
					g2d.draw (new Line2D.Float
						(L+c*W+c*L+W-2*M, L+r*W+r*L+2*M,
						 L+c*W+c*L+2*M,   L+r*W+r*L+W-2*M));
					}

				// Draw an O.
				else if (state.getMark (i) == Mark.O)
					{
					g2d.setColor (O_COLOR);
					g2d.setStroke (MARK_STROKE);
					g2d.draw (new Ellipse2D.Float
						(L+c*W+c*L+2*M,   L+r*W+r*L+2*M, W-4*M, W-4*M));
					}
				}
		}

	}
