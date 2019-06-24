package problema.pkg2.servidor.ciphers;

/**
 *
 * @author Gabriel
 */
public class Util {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz"; // sem ç
    
    public static String getShiftedAlphabet(int key) {
        return Util.alphabet.substring(key) + Util.alphabet.substring(0, key);
    }
    
    public static String removeSpecialCharacters(String text) {
        return text.replaceAll("[^A-Z]", "");
    }
}
