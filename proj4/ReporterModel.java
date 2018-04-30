import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.NoSuchFileException;
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
    private RSA rsa;

    /**
     * 
     * Constructor for the ReporterModel
     * 
     * @param privateKeyFile - private file
     * 
     */
    public ReporterModel(String file) {
        this.rsa = new RSA(file);
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
            System.out.println(rsa.decode(cipher));
        } catch(Exception e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}
