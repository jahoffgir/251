import java.math.BigInteger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Random;

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
    private String fileName;

    /**
     * RSA constructor 
     * 
     * @param fileName - file
     */
    public RSA (String fileName) {
        this.fileName = fileName;
    }

    /**
     * Encodes the message
     * 
     * @param publicKeyFile - the file
     * @param message - message
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
            BigInteger [] result = getFromFile(fileName);
            // c = m^e (mod n) encoding it
            c = plaintext.modPow(result[0], result[1]); 
        } catch (Exception e) {
            System.out.println("ERROR");
            System.exit(1);
        }
        return c.toByteArray();

    }

    /**
     * Gets the exponent and modulus from file
     * 
     * @param fileName - the file
     */
    private BigInteger[] getFromFile(String fileName) {
        BigInteger [] result = new BigInteger [2];
        try {
            // read the publickey file and extract the exponent and the modulus
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
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
            System.exit(1);
        }
        return result;
    }

    /**
     * 
     * Decodes the message
     * 
     * @param cipher - ciphertext
     * @param privateKeyFile - the file
     */
    public String decode(byte [] cipher) {
        String decode = "";
        try {
            BigInteger cipherText = new BigInteger(cipher);
            BigInteger [] result = getFromFile(fileName);
            // decoding 
            BigInteger plaintext = cipherText.modPow(result[0], result[1]);
            OAEP op = new OAEP();
            decode= op.decode(plaintext);
        } catch (Exception e) {
            System.out.println("ERROR");
            System.exit(1);
        }
        return decode;
    }

}
