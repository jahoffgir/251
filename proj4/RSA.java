import java.math.BigInteger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Random;
import java.nio.file.NoSuchFileException;
/**
 * 
 * Class RSA that will encode and decode the message
 * 
 * @author Jahongir Amirkulov
 * @version 04/25/18
 * 
 */
public class RSA {

    // hidden variables
    private File files;
    /**
     * RSA constructor 
     * 
     * @param file - file name
     * 
     */
    public RSA(String file) {
        this.files = new File(file);
        if (!files.exists()) {
            System.err.println("No such file exists");
            System.exit(1);
        }
    }

    /**
     * Encodes the message
     * 
     * @param message - message
     * 
     */
    public byte[] encode(String message) {
        BigInteger c = BigInteger.ZERO;
        try {
            OAEP op = new OAEP();
            // seed
            byte [] bt = new byte[32];
            new Random().nextBytes(bt);
            // Plain text
            BigInteger plaintext = op.encode(message, bt);
            BigInteger [] result = getFromFile();
            // c = m^e (mod n) encoding it
            c = plaintext.modPow(result[0], result[1]); 
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return c.toByteArray();

    }

    /**
     * Gets the exponent and modulus from file
     * 
     */
    private BigInteger[] getFromFile() {
        BigInteger [] result = new BigInteger [2];
        try {
            // read the publickey file and extract the exponent and the modulus
            FileReader fileReader = new FileReader(files);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int increment = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (increment == 0) result[0] = new BigInteger(line);
                else if (increment == 1) result[1] = new BigInteger(line);
                increment++;
            }
            fileReader.close();  
        } catch (IOException a) {
            System.err.println("ERROR");
        }
        return result;
    }

    /**
     * 
     * Decodes the message
     * 
     * @param cipher - ciphertext
     *
     */
    public String decode(byte [] cipher) {
        String decode = "";
        try {
            BigInteger cipherText = new BigInteger(cipher);
            BigInteger [] result = getFromFile();
            // decoding 
            BigInteger plaintext = cipherText.modPow(result[0], result[1]);
            OAEP op = new OAEP();
            decode= op.decode(plaintext);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return decode;
    }

}
