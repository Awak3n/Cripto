package problema.pkg2.cliente;

/**
 *
 * @author Leonardo
 */
public class JPanelMain extends javax.swing.JPanel {
    /**
     * Creates new form JPanelMain
     */
    public JPanelMain() {
        initComponents();
        jTextArea2.append("Instruções:\n"
                + "\n Preencha o IP do host e"
                + "\n conecte."
                + "\n\n Insira o texto na aba de"
                + "\n mensagem."
                + "\n\n Insira a chave no campo"
                + "\n de chave."
                + "\n\n Selecione a criptografia."
                + "\n\n Escolha entre criptar e" 
                + "\n decriptar."
        );
        jTextArea2.setEditable(false);
        jComboBox1.setVisible(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        this.setSize(1280,400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1280, 600));
        setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(1086, 50, 190, 310);

        jLabel2.setText("Resultado:");
        add(jLabel2);
        jLabel2.setBounds(830, 70, 75, 14);

        jLabel1.setText("Mensagem:");
        add(jLabel1);
        jLabel1.setBounds(377, 71, 75, 14);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 30, 180, 270);

        jButton1.setText("Conectar");
        add(jButton1);
        jButton1.setBounds(10, 350, 180, 30);

        jLabel3.setText("Mensagens:");
        add(jLabel3);
        jLabel3.setBounds(1140, 30, 90, 20);

        jTextField1.setText("IP do servidor");
        add(jTextField1);
        jTextField1.setBounds(10, 310, 180, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4.setText("Cripto");
        add(jLabel4);
        jLabel4.setBounds(560, 10, 150, 60);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        add(jScrollPane3);
        jScrollPane3.setBounds(650, 100, 410, 220);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        add(jScrollPane4);
        jScrollPane4.setBounds(220, 100, 410, 180);

        jButton2.setText("Enviar");
        add(jButton2);
        jButton2.setBounds(830, 330, 63, 30);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2);
        jTextField2.setBounds(280, 290, 350, 30);

        jLabel5.setText("Chave:");
        add(jLabel5);
        jLabel5.setBounds(220, 300, 50, 14);

        jButton4.setText("Encriptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(550, 330, 80, 30);

        jButton5.setText("Decriptar");
        add(jButton5);
        jButton5.setBounds(220, 330, 80, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ECB", "CBC", "CFB", "OFB", "CTR" }));
        add(jComboBox1);
        jComboBox1.setBounds(430, 330, 110, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cesar", "Vigenère", "One-Time Pad", "Playflair", "Hill", "DES", "3DES", "AES" }));
        add(jComboBox2);
        jComboBox2.setBounds(310, 330, 110, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JComboBox<String> jComboBox1;
    public javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea2;
    public javax.swing.JTextArea jTextArea3;
    public javax.swing.JTextArea jTextArea4;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
