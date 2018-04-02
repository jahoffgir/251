//******************************************************************************
//
// File:    TicTacToeServer.java
// Package: ---
// Unit:    Class TicTacToeServer
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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class TicTacToeServer is the server main program for the Tic-Tac-Toe network
 * game.
 *
 * @author  Alan Kaminsky
 * @version 26-Feb-2018
 */
public class TicTacToeServer
	{

	/**
	 * Main program.
	 */
	public static void main
		(String[] args)
		{
		// Parse command line arguments.
		if (args.length != 2) usage();
		String host = args[0];
		int port = parseInt (args[1], "<port>");

		try
			{
			// Listen for connections from clients.
			ServerSocket serversocket = new ServerSocket();
			serversocket.bind (new InetSocketAddress (host, port));

			// Session management logic.
			TicTacToeModel model = null;
			for (;;)
				{
				Socket socket = serversocket.accept();
				ViewProxy proxy = new ViewProxy (socket);
				if (model == null || model.isFinished())
					{
					model = new TicTacToeModel();
					proxy.setListener (model);
					}
				else
					{
					proxy.setListener (model);
					model = null;
					}
				}
			}
		catch (IOException exc)
			{
			error (exc);
			}
		}

	/**
	 * Parse an integer.
	 *
	 * @param  s      String to parse.
	 * @param  label  Error message label.
	 *
	 * @return  Integer value.
	 */
	private static int parseInt
		(String s,
		 String label)
		{
		int i = 0;
		try
			{
			i = Integer.parseInt (s);
			}
		catch (NumberFormatException exc)
			{
			errorUsage (String.format ("%s = \"%s\" illegal", label, s));
			}
		return i;
		}

	/**
	 * Print an error message and a usage message and terminate.
	 *
	 * @param  msg  Message.
	 */
	private static void errorUsage
		(String msg)
		{
		System.err.printf ("TicTacToeServer: %s%n", msg);
		usage();
		}

	/**
	 * Print a usage message and terminate.
	 */
	private static void usage()
		{
		System.err.println ("Usage: java TicTacToeServer <host> <port>");
		System.exit (1);
		}

	/**
	 * Print an I/O error message and terminate.
	 *
	 * @param  exc  Exception.
	 */
	private static void error
		(IOException exc)
		{
		System.err.println ("TicTacToeServer: I/O error");
		exc.printStackTrace (System.err);
		System.exit (1);
		}

	}
