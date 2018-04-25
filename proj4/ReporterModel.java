import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class ReporterModel {
    
    private String privatekeyFile;

    /**
     * 
     * 
     */
    public ReporterModel(String privateKeyFile) {
        this.privatekeyFile = privateKeyFile;
    }
    /**
     * 
     * 
     * 
     */
    public void decode(byte[] cipher) {
        try {
            BigInteger cipherText = new BigInteger(cipher);
            File file = new File(privatekeyFile);
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

            BigInteger plaintext = cipherText.modPow(exponentE, modulusM);
            OAEP op = new OAEP();
            String decode = op.decode(plaintext);
            System.out.println(decode);
        } catch(Exception e) {
            System.out.println("ERROR1");
            System.exit(1);
        }
    }
}
