import java.math.BigInteger;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;
public class RSA {

    public static byte[] encode(String publicKeyFile, String message) {
        OAEP op = new OAEP();
        // seed
        byte [] bt = new byte[32];
        new Random().nextBytes(bt);
        // Plain text
        BigInteger plaintext = op.encode(message, bt);

        BigInteger [] result = getFromFile(publicKeyFile);

        // c = m^e (mod n) encoding it
        BigInteger c = plaintext.modPow(result[0], result[1]);
        return c.toByteArray();

    }

    private static BigInteger[] getFromFile(String fileName) {
        BigInteger [] result = new BigInteger [2];

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
        return result;
    }
    public static String decode(byte [] cipher, String privatekeyFile) {
        BigInteger cipherText = new BigInteger(cipher);
        
        BigInteger [] result = getFromFile(privateKeyFile);

        // decoding 
        BigInteger plaintext = cipherText.modPow(result[0], result[1]);
        OAEP op = new OAEP();
        String decode = op.decode(plaintext);
        return decode;
    }

}