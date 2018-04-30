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
    private BigInteger exponent;
    private BigInteger modulus;

    /**
     * RSA constructor 
     * 
     * @param file - file name
     * 
     */
    public RSA(String file) {
        File files = new File(file);
        if (!files.exists()) {
            System.err.println("No such file exists");
            System.exit(1);
        }
        try {
            // read the publickey file and extract the exponent and the modulus
            FileReader fileReader = new FileReader(files);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int increment = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (increment == 0) exponent = new BigInteger(line);
                else if (increment == 1) modulus = new BigInteger(line);
                increment++;
            }
            fileReader.close();  
        } catch (IOException a) {
            System.out.print("ERROR");
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
            // c = m^e (mod n) encoding it
            c = plaintext.modPow(exponent, modulus); 
        } catch (Exception e) {
            System.out.print("ERROR");
        }
        return c.toByteArray();

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
            // decoding 
            BigInteger plaintext = cipherText.modPow(exponent, modulus);
            OAEP op = new OAEP();
            decode= op.decode(plaintext);
        } catch (Exception e) {
            System.out.print("ERROR");
        }
        return decode;
    }
}
