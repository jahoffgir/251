import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Class LeakerProxy for the Leaker. It sends the the encoded message to the Reporter.
 * 
 * @author Jahongir Amirkulov
 * @version 04/25/18
 * 
 */
public class LeakerProxy {

    // private variables
    private DatagramSocket reporter;
    private SocketAddress destination;
    

    /**
     * 
     * LeakerProxy Constructor for LeakerProxy
     * 
     * @param report - datagram socket
     * @param destination - destination
     */
    public LeakerProxy(DatagramSocket reporter, SocketAddress destination) {
        this.reporter = reporter;
        this.destination = destination;
    }

    
    /**
    * Report the state of a fire sensor.
    *
    * @param cipher - cipher that is being sent
    *
    * @exception  IOException
    *     Thrown if an I/O error occurred.
    */
    public void encode(byte [] cipher) throws IOException {
        try {
            reporter.send(new DatagramPacket(cipher, cipher.length, destination));
        } catch (IOException e) {
            System.err.println("Error in the LeakerProxy.");
            System.exit(1);
        }
    }

}
