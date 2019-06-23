package problema.pkg2.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ActionController implements ActionListener, ItemListener{
    MainClient main;
    
    ActionController(MainClient main){
        this.main = main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == main.jm.jButton1){
            //inicializa o cliente
            main.client.startRunning(main.jm.jTextField1.getText());
            //envia os dados de inserção
            main.jm.jButton1.setEnabled(false);
            main.jm.jTextField1.setEnabled(false);
        } else if(e.getSource() == main.jm.jButton2){
            String txt = main.jm.jTextArea3.getText();
            main.client.sendData(txt);
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
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
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
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
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
        Cipher c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "BC");
        Key key = new SecretKeySpec(keyValue,tipo);
        if(modo.equals("CBC") || modo.equals("ECB")){
            c.init(Cipher.ENCRYPT_MODE, key);
        }else{
            byte[] ivBytes;
            ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            c.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        }
        byte[] cipherText = new byte[c.getOutputSize(main.jm.jTextArea4.getText().getBytes().length)];
        System.out.println(new String(cipherText));
        byte[] encVal = c.doFinal(main.jm.jTextArea4.getText().getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    private String getDecript(byte[] keyValue, String modo, String tipo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher c = Cipher.getInstance(tipo + "/" + modo + "/PKCS5Padding", "BC");
        Key key = new SecretKeySpec(keyValue,tipo);
        if(modo.equals("ECB")){
            c.init(Cipher.DECRYPT_MODE, key);
        }else{
            byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            c.init(Cipher.DECRYPT_MODE, key, ivSpec);
        }
        byte[] decordedVal = new BASE64Decoder().decodeBuffer(main.jm.jTextArea4.getText());
        byte[] decVal = c.doFinal(decordedVal);
        String decryptedValue = new String(decVal);
        return decryptedValue;
    }
    
    private void encriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        String text = "";
        byte[] keyValue = main.jm.jTextField2.getText().getBytes();
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            if(main.jm.jComboBox2.getSelectedIndex() == 0){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){
                
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
                text = getEncript(keyValue, "ECB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getEncript(keyValue, "CBC", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getEncript(keyValue, "CFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getEncript(keyValue, "OFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
                text = getEncript(keyValue, "CTR", "AES");
            }
        //as seguintes linhas servem apenas para testes
            main.jm.jTextArea3.setText(text);
        //fim dos testes
            main.jm.jButton2.setEnabled(true);
        }
    }
    
    private void decriptar() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException{
        String text = "";
        byte[] keyValue = main.jm.jTextField2.getText().getBytes();
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
            if(main.jm.jComboBox2.getSelectedIndex() == 0){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 1){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 2){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 3){
                
            } else if(main.jm.jComboBox2.getSelectedIndex() == 4){
                
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
                text = getDecript(keyValue, "ECB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 1){
                text = getDecript(keyValue, "CBC", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 2){
                text = getDecript(keyValue, "CFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 3){
                text = getDecript(keyValue, "OFB", "AES");
            } else if(main.jm.jComboBox2.getSelectedIndex() == 7 && main.jm.jComboBox1.getSelectedIndex() == 4){
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
    
    //controla os dados que chegam do servidor, marcando no campo do cliente
    public final void dataManagerS() throws IOException, ClassNotFoundException{
        String msg;
        do{
            msg = main.client.receiveData();
            showMessage("\nO outro diz:\n" + msg);
        }while(true);
    }
}
