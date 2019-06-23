package problema.pkg2.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ActionController implements ActionListener, ItemListener{
    MainServer main;
    
    ActionController(MainServer main){
        this.main = main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == main.jm.jButton2){
            String txt = main.jm.jTextArea4.getText();
            main.server.sendData(main.jm.jTextArea3.getText());
            showMessage("\nVocê diz:\n" + txt);
        } else if(e.getSource() == main.jm.jButton4){
            try {
                encriptar();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(e.getSource() == main.jm.jButton5){
             try {
                decriptar();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
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
    
    private String getEncript(byte[] keyValue, String modo, String tipo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, IOException, IOException{
        Cipher c = Cipher.getInstance(modo);
        Key key = new SecretKeySpec(keyValue,tipo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(main.jm.jTextArea4.getText().getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    private String getDecript(byte[] keyValue, String modo, String tipo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
        Cipher c = Cipher.getInstance(modo);
        Key key = new SecretKeySpec(keyValue,tipo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedVal = new BASE64Decoder().decodeBuffer(main.jm.jTextArea4.getText());
        byte[] decVal = c.doFinal(main.jm.jTextArea4.getText().getBytes());
        String decryptedValue = new String(decVal);
        return decryptedValue;
    }
    
    private void encriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
        String text = "";
        byte[] keyValue = main.jm.jTextField2.getText().getBytes();
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            if(main.jm.jComboBox2.getSelectedIndex() == 0){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 4){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getEncript(keyValue, "DES/ECB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "DES/CBC/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "DES/CFB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "DES/OFB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "DES/CTR/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getEncript(keyValue, "DESede/ECB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "DESede/CBC/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "DESede/CFB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "DESede/OFB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "DESede/CTR/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getEncript(keyValue, "AES/ECB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "AES/CBC/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "AES/CFB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "AES/OFB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "AES/CTR/NoPadding", "AES");
            }
        //as seguintes linhas servem apenas para testes
            main.jm.jTextArea3.setText(text);
        //fim dos testes
            main.jm.jButton2.setEnabled(true);
        }
    }
    
    private void decriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
        String text = "";
        byte[] keyValue = main.jm.jTextField2.getText().getBytes();
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            if(main.jm.jComboBox2.getSelectedIndex() == 0){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 4){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getDecript(keyValue, "DES/ECB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "DES/CBC/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "DES/CFB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "DES/OFB/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 5 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getDecript(keyValue, "DES/CTR/NoPadding", "DES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getDecript(keyValue, "DESede/ECB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "DESede/CBC/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "DESede/CFB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "DESede/OFB/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 6 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getDecript(keyValue, "DESede/CTR/NoPadding", "DESede");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 0){
                text = getDecript(keyValue, "AES/ECB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "AES/CBC/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "AES/CFB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "AES/OFB/NoPadding", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getDecript(keyValue, "AES/CTR/NoPadding", "AES");
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