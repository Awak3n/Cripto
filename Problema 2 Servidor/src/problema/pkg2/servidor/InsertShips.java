/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema.pkg2.servidor;

/**
 *
 * @author super
 */
public class InsertShips extends javax.swing.JPanel {
    private char orient = 'v'; //orientação de cada botão
    private int rest = 5; //quantidade de navios a serem inseridos
    boolean gStart = true; //variável de controle para inserção dos navios
    /**
     * Creates new form InsertShips
     */
    public InsertShips() {
        initComponents();
        this.setSize(230,450);
        jTextArea1.setEditable(false);
        jButton3.setEnabled(false);
    }
    
    public char getOrient(){
        return orient;
    }
    public void setOrient(char orient){
        this.orient = orient;
    }
    public int getRest(){
        return rest;
    }
    public void subRest(){
        if(rest >= 0)
            rest--;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();

        setLayout(null);

        jButton1.setText("Vertical");
        add(jButton1);
        jButton1.setBounds(120, 0, 100, 25);

        jButton2.setText("Horizontal");
        add(jButton2);
        jButton2.setBounds(10, 0, 100, 25);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Para inserir os navios:\n 1- Selecione a orientação.\n 2- Pressione a posição desejada.\n Obs.: Se não houver espaço\n  para o navio ser inserido\n  na posição escolhida, o mesmo\n  será inserido no sentido contrário.\n\nTipos de navios/tamanho:\n Porta-Aviões - 5 unidades.\n Navio-Tanque - 4 unidades.\n Contratorpedeiro - 3 Unidades.\n Cargueiro - 2 unidades.\n Submarino - 1 unidades.\n\nA inserção ocorrerá do maior\n para o menor, indo da posição\n mais a esquerda ou acima\n para a mais baixa ou a direita.\n\nAo terminar pressione OK.\n");
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 210, 390);

        jButton3.setText("OK");
        add(jButton3);
        jButton3.setBounds(70, 420, 80, 25);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
