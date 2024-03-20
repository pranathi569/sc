import javax.crypto.*;
import java.util.*;

public class AES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plain text : ");
        String pt = sc.nextLine();
        SecretKey key = generateAESKey();
        Cipher ct = Cipher.getInstance("AES");
        ct.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = ct.doFinal(pt.getBytes());
        String enBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text : " + enBase64);
        ct.init(Cipher.DECRYPT_MODE, key);
        byte[] decB = ct.doFinal(Base64.getDecoder().decode(enBase64));
        String dt = new String(decB);
        System.out.println("Decrypted Text : " + dt);
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }
}