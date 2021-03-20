/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import controller.ControllerConta;
import controller.ControllerMovimentacaoBancaria;
import controller.ControllerPessoaFisica;
import controller.ControllerPessoaJuridica;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.classes.Conta;
import model.classes.MovimentacaoBancaria;

/**
 *
 * @author Willian-PC
 */
public class FrmLogin extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    Conta conta  = new Conta();
    float valor = 0;
    public FrmLogin() {
        initComponents();
        try {
            MaskFormatter maskConta = new MaskFormatter("#.###-#");
            MaskFormatter maskAgencia = new MaskFormatter("####");
            
            maskConta.install(ftxtNumeroDaConta);
            maskAgencia.install(ftxtAgencia);
        } catch (ParseException ex) {
            System.err.println("Erro: "+ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        pswSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCadastro = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        txtLogin = new javax.swing.JTextField();
        pnlCaixa = new javax.swing.JPanel();
        lblImagemCaixa = new javax.swing.JLabel();
        btnDepositar = new javax.swing.JButton();
        cbxTipoDaConta = new javax.swing.JComboBox<>();
        ftxtAgencia = new javax.swing.JFormattedTextField();
        lblAgencia = new javax.swing.JLabel();
        lblNumeroDaConta = new javax.swing.JLabel();
        ftxtNumeroDaConta = new javax.swing.JFormattedTextField();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnCancelar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frmLogin");

        pnlLogin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Banck System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N
        pnlLogin.setToolTipText("");
        pnlLogin.setAutoscrolls(true);

        lblLogin.setText("CPF/CNPJ:");

        lblSenha.setText("Senha:");

        pswSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaActionPerformed(evt);
            }
        });

        btnLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\confirmar.png")); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblCadastro.setText("Não possui cadastro? Click no botão cadastrar:");

        btnCadastrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\novo.jpeg")); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSenha)
                    .addComponent(lblLogin))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCadastro)
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLogin)))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pswSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtLogin))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(120, 120, 120))))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblSenha)
                .addGap(3, 3, 3)
                .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnLogin))
                .addGap(28, 28, 28)
                .addComponent(lblCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCaixa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblImagemCaixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\caixaBanck.png")); // NOI18N

        btnDepositar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\deposito.png")); // NOI18N
        btnDepositar.setText("Depositar");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        cbxTipoDaConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente Pessoa Física", "Conta Corrente Pessoa Juridica", "Poupança Pessoa Física", "Poupança Pessoa Juridica" }));
        cbxTipoDaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDaContaActionPerformed(evt);
            }
        });

        lblAgencia.setText("Agência:");

        lblNumeroDaConta.setText("Número da conta:");

        lblValor.setText("Valor:");

        btnCancelar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCaixaLayout = new javax.swing.GroupLayout(pnlCaixa);
        pnlCaixa.setLayout(pnlCaixaLayout);
        pnlCaixaLayout.setHorizontalGroup(
            pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagemCaixa)
                .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlCaixaLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlCaixaLayout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlCaixaLayout.createSequentialGroup()
                                            .addComponent(lblNumeroDaConta)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlCaixaLayout.createSequentialGroup()
                                            .addComponent(lblAgencia)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(ftxtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(pnlCaixaLayout.createSequentialGroup()
                                    .addComponent(btnCancelar1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDepositar)
                                    .addGap(10, 10, 10))))
                        .addGroup(pnlCaixaLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCaixaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCaixaLayout.setVerticalGroup(
            pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixaLayout.createSequentialGroup()
                .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCaixaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImagemCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCaixaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroDaConta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAgencia)
                            .addComponent(ftxtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValor)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos(){
        ftxtNumeroDaConta.setText("");
        ftxtAgencia.setText("");
        txtValor.setText("");
        cbxTipoDaConta.setSelectedIndex(0);
    }
    
    public boolean validaCampos(){
        int tipo = 0;
        switch (cbxTipoDaConta.getSelectedIndex()){
            case 0: 
                tipo = 2;
                break;
            case 1: 
                tipo = 3;
                break;
            case 2: 
                tipo = 4;
                break;
            case 3: 
                tipo = 5;
                break;
        }
        conta = ControllerConta.confirmaConta(ftxtNumeroDaConta.getText(), tipo);
        if(conta == null){
            JOptionPane.showMessageDialog(rootPane, "Conta informada errado");
            ftxtNumeroDaConta.requestFocus();
            return false;
        }
        if(!"0001".equals(ftxtAgencia.getText())){
            JOptionPane.showMessageDialog(rootPane, "Agencia informada errado");
            ftxtAgencia.requestFocus();
            return false;
        }
        try{
            valor = Float.parseFloat(txtValor.getText());
            if(valor < 5){
                JOptionPane.showMessageDialog(rootPane, "O valor deve ser pelo menos 5");
                txtValor.requestFocus();
                return false;
            }
            return true;
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "O valor deve conter apenas números");
            txtValor.requestFocus();
            return false;
        }
    }
    
    private void pswSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // cancelar
        txtLogin.setText("");
        pswSenha.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // cadastrar
        new FrmSelectCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // confirmar
        String login = txtLogin.getText();
        String senha  = ""+Arrays.toString(pswSenha.getPassword());
        senha = senha.replace(" ", "").replace(",", "").replace("[", "").replace("]", "");
        switch (login.length()){
            case 11:
                login = login.substring(0,3)+"."+login.substring(3,6) + "."+login.substring(6,9) + "-"+login.substring(9,11);
                if(ControllerPessoaFisica.login(login, senha)){
                    new FrmHome(login,0).setVisible(true);
                    this.dispose();
                }else
                    JOptionPane.showMessageDialog(rootPane, "Login incorreto");
                break;

            case 14:
                login = login.substring(0,2)+"."+login.substring(2,5) + "."+login.substring(5,8) + "/"+login.substring(8,12)+ "-"+login.substring(12,14);
                if(ControllerPessoaJuridica.login(login, senha)){
                   new FrmHome(login,0).setVisible(true);
                   this.dispose();
                } else 
                    JOptionPane.showMessageDialog(rootPane, "Login incorreto");
                break;

            default:
                JOptionPane.showMessageDialog(rootPane, "Login incorreto");
                break;
        } 
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        // depositar
        if(validaCampos()){
            Date hoje = new Date();
            String descricao ="Depósito";
            if(ControllerMovimentacaoBancaria.insert(conta.getId(), 'C', hoje, 6, descricao, Float.parseFloat(txtValor.getText()))){
                descricao ="saldo de "+hoje;
                ControllerMovimentacaoBancaria.insert(conta.getId(), 'S', hoje, 1, descricao, ControllerConta.atualizaSaldo(conta.getId()));
                JOptionPane.showMessageDialog(rootPane,"Depósito realizado com sucesso");
                int confirma = JOptionPane.showConfirmDialog(rootPane, "deseja realizar outro depósito?","",JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION)
                    ftxtNumeroDaConta.requestFocus();
                limpaCampos(); 
            }
            else
            JOptionPane.showMessageDialog(rootPane,"Erro no depósito");
        }
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void cbxTipoDaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDaContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDaContaActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> cbxTipoDaConta;
    private javax.swing.JFormattedTextField ftxtAgencia;
    private javax.swing.JFormattedTextField ftxtNumeroDaConta;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblCadastro;
    private javax.swing.JLabel lblImagemCaixa;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNumeroDaConta;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlCaixa;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
