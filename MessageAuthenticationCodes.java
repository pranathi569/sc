import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class MessageAuthenticationCodes {
   public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter secret message:");
       String secret = sc.nextLine();
    System.out.println("Enter message:");
       String message = sc.nextLine();

       try {
           Mac hasher = Mac.getInstance("HmacSHA256");
           SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
           hasher.init(secretKey);

           byte[] hashedBytes = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));
           String encodedHash = Base64.getEncoder().encodeToString(hashedBytes);

           System.out.println("HMAC : " + encodedHash);
       } catch (Exception e) {
           System.out.println("Error generating HMAC : " + e.getMessage());
       }
   }
}

