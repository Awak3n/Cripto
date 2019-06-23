package problema.pkg2.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

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
            String txt = main.jm.jTextArea4.getText();
            main.client.sendData(main.jm.jTextArea3.getText());
            showMessage("\nVocê diz:\n" + txt);
        } else if(e.getSource() == main.jm.jButton4){
            encriptar();
        } else if(e.getSource() == main.jm.jButton5){
            decriptar();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getItem().equals("DES") || e.getItem().equals("3DES") || e.getItem().equals("AES")){
                main.jm.jComboBox1.setVisible(true);
            }else{
                main.jm.jComboBox1.setVisible(false);
            }
        }  
    }
    
    private void encriptar(){
        //TODO
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
        //as seguintes linhas servem apenas para testes
            main.jm.jTextArea3.setText(main.jm.jTextArea4.getText());
        //fim dos testes
            main.jm.jButton2.setEnabled(true);
        }
    }
    
    private void decriptar(){
        //TODO
        if(main.jm.jTextArea4.getText() != null && !main.jm.jTextArea4.getText().equals("")){
        //as seguintes linhas servem apenas para testes
            String txt = main.jm.jTextArea4.getText();
            main.jm.jTextArea3.setText(txt);
        //fim dos testes
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
