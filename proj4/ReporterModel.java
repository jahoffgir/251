import java.math.BigInteger;

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
            File file = new File(publicKeyFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int increment = 0;
            BigInteger exponentE;
            BigInteger modulusM;
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
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}