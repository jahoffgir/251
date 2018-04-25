import java.math.BigInteger;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;
import java.io.IOException;

/**
 * 
 * Class LeakerModel that will do the logic behind the Leaker.
 * 
 * @author Jahongir Amirkulov
 * @version 04/25/18
 */
public class LeakerModel {

    // Hidden variables
    private String message;
    private ReporterProxy proxy;
    private String publicKeyFile;

    /**
     * LeakerModel constructor for LeakerModel
     * 
     * @param message - message
     * @param proxy - the leaker proxy
     * @param publicKeyFile - public file
     * 
     */
    public LeakerModel(String message, ReporterProxy proxy, String publicKeyFile) {
        this.message = message;
        this.proxy = proxy;
        this.publicKeyFile = publicKeyFile;
        encode();
    }

    /**
     * 
     * Encodes the message and the LeakerProxy will send it to the Reporter
     * 
     */
    public void encode() {
        try {
            RSA rsa = new RSA(publicKeyFile);
            proxy.encode(rsa.encode(message));
        } catch (IOException exc) {
            System.err.println("Error in the Leaker Model.");
            System.exit(1);
        }
    }
}
