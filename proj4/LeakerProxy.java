import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class LeakerProxy {

    // private variables
    private DatagramSocket reporter;
    private SocketAddress destination;
    

    /**
     * 
     */
    public LeakerProxy(DatagramSocket reporter, SocketAddress destination) {
        this.reporter = reporter;
        this.destination = destination;
    }

    
    /**
    * Report the state of a fire sensor.
    *
    * @param  message    the message that needs to be encoded
    * @param  timestamp  Time stamp.
    * @param  fire       True if there's a fire, false if not.
    *
    * @exception  IOException
    *     Thrown if an I/O error occurred.
    */
    public void encode(byte [] cipher) throws IOException {
        reporter.send(new DatagramPacket(cipher, cipher.length, destination));
    }

}
