//******************************************************************************
//
// File:    SensorProxy.java
// Package: ---
// Unit:    Class SensorProxy
//
// This Java source file is copyright (C) 2017 by Alan Kaminsky. All rights
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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Class SensorProxy provides the network proxy for the fire sensor objects in
 * the Fire Alarm Simulation. The sensor proxy resides in the fire station
 * program and communicates with the fire sensor programs.
 *
 * @author  Alan Kaminsky
 * @version 10-Feb-2017
 */
public class ReporterProxy
	{

	// Hidden data members. 
	private DatagramSocket mailbox;
	private ReporterModel reporter;

	/**
	 * Construct a new sensor proxy.
	 *
	 * @param  mailbox  Mailbox.
	 *
	 * @exception  IOException
	 *     Thrown if an I/O error occurred.
	 */
	public ReporterProxy(DatagramSocket mailbox, ReporterModel reporter) {
		this.mailbox = mailbox;
		this.reporter = reporter;
	}
	public void start() {
		new ReaderThread() .start();
	}
	public void decode() {
		byte[] payload = new byte[260]; 
		// byte[] cipher;
		try {
			for (;;) {
				DatagramPacket packet = new DatagramPacket (payload, payload.length);
				mailbox.receive (packet);
				DataInputStream in = new DataInputStream(new ByteArrayInputStream(payload, 0, packet.getLength()));
				byte b = in.readByte();
				switch (b) {
					case 'E':
						int read = 0;
						while ((read = in.read(payload, 0, payload.length)) != -1) {
							in.read(payload);
						}
						reporter.decode(payload);
						break;
					default:
						System.out.println("ERROR");
						System.exit(1);
						break;
				}
			}
		} catch (IOException exc) {
			System.out.println("ERROR");
			System.exit (1);
		}
	}

	/**
	 * Class ReaderThread receives messages from the network, decodes them, and
	 * invokes the proper methods to process them.
	 */
	private class ReaderThread extends Thread {
		public void run() {
			byte[] payload = new byte[260]; 
			// byte[] cipher;
			try {
				for (;;) {
					DatagramPacket packet = new DatagramPacket (payload, payload.length);
					mailbox.receive (packet);
					DataInputStream in = new DataInputStream(new ByteArrayInputStream(payload, 0, packet.getLength()));
					byte b = in.readByte();
					switch (b) {
						case 'E':
							int read = 0;
							while ((read = in.read(payload, 0, payload.length)) != -1) {
								in.read(payload);
							}
							reporter.decode(payload);
							break;
						default:
							System.out.println("ERROR");
							System.exit(1);
							break;
					}
				}
			} catch (IOException exc) {
				System.out.println("ERROR");
				System.exit (1);
			}
		}
	}

}