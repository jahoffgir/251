import java.math.BigInteger;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;
import java.io.IOException;

public class LeakerModel {
    private String message;
    private LeakerProxy proxy;
    private String publicKeyFile;
    public LeakerModel(String message, LeakerProxy proxy, String publicKeyFile) {
        this.message = message;
        this.proxy = proxy;
        this.publicKeyFile = publicKeyFile;
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
            BigInteger exponentE = new BigInteger("0");
            BigInteger modulusM = new BigInteger("0");
			String line;
			while ((line = bufferedReader.readLine()) != null) {
                if (increment == 0) exponentE = new BigInteger(line);
                else if (increment == 1) modulusM = new BigInteger(line);
                increment++;
			}
            fileReader.close();
            
            // c = m^e (mod n)
            BigInteger c = plaintext.modPow(exponentE, modulusM);
            byte [] cipher = c.toByteArray();
            proxy.encode(cipher);
        } catch (IOException exc) {
            exc.printStackTrace (System.err);
            System.exit (1);
        }
    }
}
