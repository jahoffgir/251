import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

/**
 * 
 * ReportModel class that will do the main logic for decoding the encoded message.
 * 
 * @author Jahongir Amirkulov
 * @version 04/25/18
 * 
 */
public class ReporterModel implements LeakerListener{
    
    // Hidden variables
    private String privatekeyFile;

    /**
     * 
     * Constructor for the ReporterModel
     * 
     * @param privateKeyFile - private file
     * 
     */
    public ReporterModel(String privateKeyFile) {
        this.privatekeyFile = privateKeyFile;
    }

    /**
     * 
     * Sends the message to be encrypted 
     * 
     * @param cipher - ciphertext
     * 
     */
    public void send(byte[] cipher) {
        try {
            RSA rsa = new RSA(privatekeyFile);
            System.out.println(rsa.decode(cipher));
        } catch(Exception e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}
