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
public class ReporterModel {
    
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
     * Decodes the ciphertext
     * 
     * @param cipher - cipher text that needs to be decoded
     * 
     */
    public void decode(byte[] cipher) {
        try {
            BigInteger cipherText = new BigInteger(cipher);
            File file = new File(privatekeyFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int increment = 0;
            // initializes E and M
            BigInteger exponentE = BigInteger.ZERO;
            BigInteger modulusM = BigInteger.ZERO;
            String line;
            // Getting E and M
            while ((line = bufferedReader.readLine()) != null) {
                if (increment == 0) exponentE = new BigInteger(line);
                else if (increment == 1) modulusM = new BigInteger(line);
                increment++;
            }
            fileReader.close();

            // decoding 
            BigInteger plaintext = cipherText.modPow(exponentE, modulusM);
            OAEP op = new OAEP();
            String decode = op.decode(plaintext);
            System.out.println(decode);
        } catch(Exception e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}
