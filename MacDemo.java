import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class MacDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // Message to be authenticated
        String message = "This is a secret message!";
        Scanner sc = new Scanner(System.in);

        // Generate a random key
        byte[] key = generateKey();

        // Create SecretKeySpec object
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");

        // Create Mac object
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);

        // Calculate MAC and convert to hex string
        byte[] macBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        String macString = bytesToHex(macBytes);

        System.out.println("Original message: " + message);
        System.out.println("Generated MAC (hex): " + macString);

        // Simulate tampering with the message
        System.out.print("Enter the tampering you want to add to the code (Enter if none): ");
        message = message + sc.nextLine();
        sc.close();

        // Recalculate MAC and verify
        mac.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] newMacBytes = mac.doFinal();
        String newMacString = bytesToHex(newMacBytes);

        if (macString.equals(newMacString)) {
            System.out.println("Message verified as authentic.");
        } else {
            System.out.println("Message has been tampered with!");
        }
    }

    private static byte[] generateKey() {
        byte[] keyBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        return keyBytes;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b & 0xFF));
        }
        return sb.toString();
    }
}