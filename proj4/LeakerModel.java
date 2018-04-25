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
    private LeakerProxy proxy;
    private String publicKeyFile;

    /**
     * LeakerModel constructor for LeakerModel
     * 
     * @param message - message
     * @param proxy - the leaker proxy
     * @param publicKeyFile - public file
     * 
     */
    public LeakerModel(String message, LeakerProxy proxy, String publicKeyFile) {
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
            OAEP op = new OAEP();
            // seed
            byte [] bt = new byte[32];
            new Random().nextBytes(bt);
            // Plain text
            BigInteger plaintext = op.encode(message, bt);

            // read the publickey file and extract the exponent and the modulus
            File file = new File(publicKeyFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
            int increment = 0;
            BigInteger exponentE = BigInteger.ZERO;
            BigInteger modulusM = BigInteger.ZERO;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
                if (increment == 0) exponentE = new BigInteger(line);
                else if (increment == 1) modulusM = new BigInteger(line);
                increment++;
			}
            fileReader.close();
            
            // c = m^e (mod n) encoding it
            BigInteger c = plaintext.modPow(exponentE, modulusM);
            byte [] cipher = c.toByteArray();
            proxy.encode(cipher);
        } catch (IOException exc) {
            System.err.println("Error in the Leaker Model.");
            System.exit(1);
        }
    }
}
