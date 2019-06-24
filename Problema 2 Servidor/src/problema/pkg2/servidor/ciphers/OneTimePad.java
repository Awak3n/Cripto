package problema.pkg2.servidor.ciphers;

/**
 *
 * @author Gabriel
 */
public class OneTimePad {
    public static String Encrypt(String text, String key) {
        String encryptedText = "";
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            char currentKeyChar = key.charAt(i);
            
            int charIndex = Util.alphabet.indexOf(Character.toLowerCase(currentChar));
            int keyCharIndex = Util.alphabet.indexOf(Character.toLowerCase(currentKeyChar));
            
            int encryptedIndex = (charIndex + keyCharIndex) % 26;
            if (encryptedIndex > 26) encryptedIndex += 26;
            char encryptedChar = Util.alphabet.charAt(encryptedIndex);
            
            encryptedText += Character.isUpperCase(currentChar) ? Character.toUpperCase(encryptedChar) : encryptedChar;
        }
        
        return encryptedText;
    }
    
    public static String Decrypt(String text, String key) {
        String decryptedText = "";
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            char currentKeyChar = key.charAt(i);
            
            int charIndex = Util.alphabet.indexOf(Character.toLowerCase(currentChar));
            int keyCharIndex = Util.alphabet.indexOf(Character.toLowerCase(currentKeyChar));
            
            int encryptedIndex = (charIndex - keyCharIndex) % 26;
            if (encryptedIndex < 0) encryptedIndex += 26;
            char encryptedChar = Util.alphabet.charAt(encryptedIndex);
            
            decryptedText += Character.isUpperCase(currentChar) ? Character.toUpperCase(encryptedChar) : encryptedChar;
        }
        
        return decryptedText;
    }
}
