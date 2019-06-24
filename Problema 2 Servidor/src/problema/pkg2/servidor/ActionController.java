package problema.pkg2.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import problema.pkg2.servidor.ciphers.*;

public class ActionController implements ActionListener, ItemListener{
    MainServer main;
    
    ActionController(MainServer main){
        this.main = main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == main.jm.jButton2){
            String txt = main.jm.jTextArea3.getText();
            main.server.sendData(txt);
            showMessage("\nVocê diz:\n" + txt);
        } else if(e.getSource() == main.jm.jButton4){
            try {
                encriptar();
            } catch (Exception ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(e.getSource() == main.jm.jButton5){
             try {
                decriptar();
            } catch (Exception ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getItem().equals("DES") || e.getItem().equals("3DES") || e.getItem().equals("AES")
                    || e.getItem().equals("ECB") || e.getItem().equals("CBC") || e.getItem().equals("CFB")
                    || e.getItem().equals("OFB") || e.getItem().equals("CTR")){
                main.jm.jComboBox1.setVisible(true);
            }else{
                main.jm.jComboBox1.setVisible(false);
            }
        }  
    }
    
    private String getEncript(byte[] keyValue, String modo, String tipo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, IOException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher c;
        byte[] encVal;
        if(tipo.equals("AES")){
            c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
            if(modo.equals("ECB")){
                c.init(Cipher.ENCRYPT_MODE, key);
            }else{
                String IV = "AAAAAAAAAAAAAAAA";
                c.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
            }
            encVal = c.doFinal(main.jm.jTextArea4.getText().getBytes("UTF-8"));
        } else {
            c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "BC");
            Key key = new SecretKeySpec(keyValue,tipo);
            if(modo.equals("CBC") || modo.equals("ECB")){
                c.init(Cipher.ENCRYPT_MODE, key);
            }else{
                byte[] ivBytes;
                ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };
                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                c.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            }
            encVal = c.doFinal(main.jm.jTextArea4.getText().getBytes());
        }
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    private String getDecript(byte[] keyValue, String modo, String tipo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher c;
        byte[] decVal;
        String decryptedValue;
        if(tipo.equals("AES")){
            c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
            if(modo.equals("ECB")){
                c.init(Cipher.DECRYPT_MODE, key);
            }else{
                String IV = "AAAAAAAAAAAAAAAA";
                c.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
            }
            byte[] decordedVal = new BASE64Decoder().decodeBuffer(main.jm.jTextArea4.getText());
            decVal = c.doFinal(decordedVal);
            decryptedValue = new String(decVal,"UTF-8");
        } else {
            c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "BC");
            Key key = new SecretKeySpec(keyValue,tipo);
            if(modo.equals("ECB")){
                c.init(Cipher.DECRYPT_MODE, key);
            }else{
                byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };
                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                c.init(Cipher.DECRYPT_MODE, key, ivSpec);
            }
            byte[] decordedVal = new BASE64Decoder().decodeBuffer(main.jm.jTextArea4.getText());
            decVal = c.doFinal(decordedVal);
            decryptedValue = new String(decVal);
        }
        return decryptedValue;
    }
    
    private void encriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        String text = "";
        String stringKey = main.jm.jTextField2.getText();
        byte[] keyValue = stringKey.getBytes();
        
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            String input = main.jm.jTextArea4.getText();
            
            if(main.jm.jComboBox2.getSelectedIndex() == 0){
                text = Caesar.Encrypt(input, Integer.parseInt(stringKey));
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){
                text = Vigenere.Encrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){
                text = OneTimePad.Encrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){
                text = Playfair.Encrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 4){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getEncript(keyValue, "ECB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "CBC", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "CFB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "OFB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "CTR", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getEncript(keyValue, "ECB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "CBC", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "CFB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "OFB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "CTR", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 0){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getEncript(keyValue, "ECB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getEncript(keyValue, "CBC", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getEncript(keyValue, "CFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getEncript(keyValue, "OFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getEncript(keyValue, "CTR", "AES");
            }
            
            main.jm.jTextArea3.setText(text);
            main.jm.jButton2.setEnabled(true);
        }
    }
    
    private void decriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        String text = "";
        String stringKey = main.jm.jTextField2.getText();
        byte[] keyValue = stringKey.getBytes();
        
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            String input = main.jm.jTextArea4.getText();
            
            if(main.jm.jComboBox2.getSelectedIndex() == 0){ // caesar
                text = Caesar.Decrypt(input, Integer.parseInt(stringKey));
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){ // vigenere
                text = Vigenere.Decrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){ // one-time pad
                text = OneTimePad.Decrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){ // playfair
                text = Playfair.Decrypt(input, stringKey);
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 4){ // hill
                
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getDecript(keyValue, "ECB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "CBC", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "CFB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "OFB", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getDecript(keyValue, "CTR", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getDecript(keyValue, "ECB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "CBC", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "CFB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "OFB", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getDecript(keyValue, "CTR", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 0){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getDecript(keyValue, "ECB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getDecript(keyValue, "CBC", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getDecript(keyValue, "CFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getDecript(keyValue, "OFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
                keyValue = main.jm.jTextField2.getText().getBytes("UTF-8");
                text = getDecript(keyValue, "CTR", "AES");
            }
            
            main.jm.jTextArea3.setText(text);
            main.jm.jButton2.setEnabled(true);
        }
    }
    
    //atualiza o status
    public final void showMessage(final String text){
        main.jm.jTextArea1.append("\n" + text);
    }
    
}