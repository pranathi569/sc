import java.util.Scanner;

public class Main {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    public static String encrypt(String plaintext, int shiftKey) {
        plaintext = plaintext.toLowerCase();
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = ALPHABET.indexOf(ch);
                if (index != -1) {
                    int newIndex = (index + shiftKey) % ALPHABET_SIZE;
                    if (newIndex < 0) {
                        newIndex += ALPHABET_SIZE;
                    }
                    ciphertext.append(ALPHABET.charAt(newIndex));
                }
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shiftKey) {
        ciphertext = ciphertext.toLowerCase();
        StringBuilder plaintext = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = ALPHABET.indexOf(ch);
                if (index != -1) {
                    int newIndex = (index - shiftKey) % ALPHABET_SIZE;
                    if (newIndex < 0) {
                        newIndex += ALPHABET_SIZE;
                    }
                    plaintext.append(ALPHABET.charAt(newIndex));
                }
            } else {
                plaintext.append(ch);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter shift key (0 to 25): ");
        int shiftKey = scanner.nextInt();

        // Encryption
        String encryptedText = encrypt(plaintext, shiftKey);
        System.out.println("Encrypted Text: " + encryptedText);
       
        System.out.println("\ncryptanalysis:");
        for (int shift = 0; shift < ALPHABET_SIZE; shift++) {
            String decryptedText = decrypt(encryptedText, shift);
            System.out.println("Shift " + shift + ": " + decryptedText);
        }

        scanner.close();
    }
}