package problema.pkg2.cliente.ciphers;

import java.awt.Point;

/**
 *
 * @author Gabriel
 * Source: Rosetta Code (adaptado)
 */
public class Playfair {
    private static char[][] charTable;
    private static Point[] positions;
    
    public static String Encrypt(String text, String key) {
        text = text.toUpperCase();
        StringBuilder sb = new StringBuilder(Util.removeSpecialCharacters(text));
        CreateTable(key);
        
        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
 
            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        
        return Codec(sb, 1);
    }
    
    public static String Decrypt(String text, String key) {
        CreateTable(key);
        return Codec(new StringBuilder(Util.removeSpecialCharacters(text)), 4);
    }
    
    private static String Codec(StringBuilder text, int direction) {
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
 
            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;
 
            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;
 
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;
 
            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }
 
            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        
        return text.toString();
    }
    
    private static void CreateTable(String key) {
        charTable = new char[5][5];
        positions = new Point[26];
        
        key = Util.removeSpecialCharacters(key);
        String text = (key + Util.alphabet).toUpperCase();
        
        for (int i = 0, k = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            if (positions[c - 'A'] == null && k / 5 < 5) {
                System.out.println("k / 5: " + k / 5 + " | k % 5: " + k % 5);
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }
}
