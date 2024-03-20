import java.util.*;
public class OneTimePad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Plain Text : ");
        String pt = sc.nextLine();
        System.out.println("Enter Key : ");
        String key = sc.nextLine();
        if(pt.length() != key.length()) {
            System.out.println("Key length must be equal to plaintext length");
            return;
        }
        StringBuilder ct = new StringBuilder();
        for(int i=0; i<pt.length(); i++) {
            char c = (char)(pt.charAt(i) ^ key.charAt(i));
            ct.append(c);
        }
        System.out.println("encrypted text : ");
        for(int i=0; i<pt.length(); i++) {
            System.out.println(Integer.toBinaryString((int)ct.charAt(i)) + " ");
        }
        StringBuilder dt = new StringBuilder();
        for(int i=0; i<ct.length(); i++) {
            char dc = (char)(ct.charAt(i) ^ key.charAt(i));
            dt.append(dc);
        }
        System.out.println("Decrypted text : " + dt.toString());
    }
}