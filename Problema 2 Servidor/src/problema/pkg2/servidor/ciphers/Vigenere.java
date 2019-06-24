package problema.pkg2.servidor.ciphers;

/**
 *
 * @author Gabriel
 * Source: Rosetta Code (adaptado)
 */
public class Vigenere {
    public static String Encrypt(String text, String key) {
        String encryptedText = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar < 'A' || currentChar > 'Z') continue;
            
            encryptedText += (char)((currentChar + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        
        return encryptedText;
    }
    
    public static String Decrypt(String text, String key) {
        String encryptedText = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            
            encryptedText += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return encryptedText;
    }
}
