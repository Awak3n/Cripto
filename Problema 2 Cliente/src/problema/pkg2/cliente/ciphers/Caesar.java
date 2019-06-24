package problema.pkg2.cliente.ciphers;

/**
 *
 * @author Gabriel
 */
public class Caesar {
    public static String Encrypt(String text, int key) {
        String shiftedAlphabet = Util.getShiftedAlphabet(key);
        String encryptedText = "";
        
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int normalIndex = Util.alphabet.indexOf(Character.toLowerCase(currentChar));
            
            char shiftedChar = shiftedAlphabet.charAt(normalIndex);
            encryptedText += Character.isUpperCase(currentChar) ? Character.toUpperCase(shiftedChar) : shiftedChar;
        }
        
        return encryptedText;
    }
    
    public static String Decrypt(String text, int key) {
        return Caesar.Encrypt(text, 26 - key); // 26 = alfabeto sem ç
    }
}
