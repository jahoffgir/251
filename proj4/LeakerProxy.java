import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Class LeakerProxy for the Reporter Class. It will get the decoded message 
 * and will call the appropriate method
 *
 * @author  Jahongir Amirkulov
 * @version 04/25/18
 */
public class LeakerProxy {

	// Hidden data members. 
	private DatagramSocket mailbox;
	private ReporterModel reporter;

	/**
	 * Construct a new reporter proxy.
	 *
	 * @param  mailbox  Mailbox.
	 * @param  reporter Reporter.
	 *
	 * @exception  IOException
	 *     Thrown if an I/O error occurred.
	 */
	public LeakerProxy(DatagramSocket mailbox, ReporterModel reporter) {
		this.mailbox = mailbox;
		this.reporter = reporter;
	}

	/**
	 * 
	 * Starts the threads running
	 * 
	 */
	public void start() {
		new ReaderThread() .start();
	}

	/**
	 * 
	 * Class ReaderThread receives messages from the network, decodes them, and
	 * invokes the proper methods to process them.
	 * 
	 */
	private class ReaderThread extends Thread {
		public void run() {
			byte[] payload = new byte[260]; 
			try {
				for (;;) {
					DatagramPacket packet = new DatagramPacket (payload, payload.length);
					mailbox.receive (packet);
                    byte[] pay = new byte[packet.getLength()];
                    for (int i = 0; i < packet.getLength(); i++) {
                        pay[i] = payload[i];
                    } 
                    reporter.send(pay);
                }
			} catch(Exception e) {
				System.out.println("ERROR");
				System.exit(1);
			}
		}
	}

}