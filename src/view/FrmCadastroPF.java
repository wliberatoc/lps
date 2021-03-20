/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerConta;
import controller.ControllerPessoaFisica;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import uteis.Uteis;

/**
 *
 * @author Willian-PC
 */
public class FrmCadastroPF extends javax.swing.JFrame {

    /**
     * Creates new form frmCadastrar
     */
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    private String senhaLogin;
    private int senha;
    
    
    public FrmCadastroPF() {
        initComponents();
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            MaskFormatter maskTelefone = new MaskFormatter("(##)# ####-####");
            
            maskData.install(ftxtNascimento);
            maskCpf.install(ftxtCpf);
            maskTelefone.install(ftxtTelefone);
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroPF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean formataSenhas(){
        String s  =  ""+Arrays.toString(pswSenha.getPassword());
        senhaLogin = ""+Arrays.toString(pswSenhaLogin.getPassword());
        senhaLogin = senhaLogin.replace(" ", "");
        senhaLogin = senhaLogin.replace(",", "");
        senhaLogin = senhaLogin.replace("[", "");
        senhaLogin = senhaLogin.replace("]", "");
        s = s.replace(" ", "");
        s = s.replace(",", "");
        s = s.replace("[", "");
        s = s.replace("]", "");
        try{
            senha = Integer.parseInt(s); 
            return true;
        }catch (NumberFormatException ex){
           System.err.println("Erro: "+ex);
           return false;
        }          
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        ftxtCpf.setText("");
        ftxtNascimento.setText("");
        ftxtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        pswSenha.setText("");
        cbxSexo.setSelectedIndex(0);
        cbxTipoConta.setSelectedIndex(0);
    }
     
    public String dadosConta(String num){
        String s;
        String numConta = "Nº conta: "+num;
        String agencia = "\nAgência: 0001";
        String tipo = "\nConta "+cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex());
        String cpf = "\nCPF: "+ftxtCpf.getText();
        s = numConta + agencia + tipo + cpf;     
        return s;
    }
       
    public boolean validaCampos(){
        if(!txtNome.getText().replace(" ", "").matches("[A-Za-z]{3,}")){
          JOptionPane.showMessageDialog(rootPane, "Preencha o nome corretamente");
          txtNome.requestFocus();
          return false;
        }
        if(ftxtCpf.getText().replace(" ", "").length() < 14 || !Uteis.verificaCPF(ftxtCpf.getText())){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CPF corretamente");
            ftxtCpf.requestFocus();
            return false;
        } 
        if(ftxtNascimento.getText().replace(" ", "").length() < 10 || !Uteis.verificaData(ftxtNascimento.getText()) || !Uteis.verificaSeTem18Anos(ftxtNascimento.getText())){
          JOptionPane.showMessageDialog(rootPane, "Preencha a data de nascimento corretamente");
          ftxtNascimento.requestFocus();
          return false;
        }
        if(!txtEmail.getText().matches("[A-Za-z0-9]+[@][A-Za-z0-9]+[.][A-Za-z0-9]+[.]*[A-Za-z0-9]*")){
          JOptionPane.showMessageDialog(rootPane, "Preencha o Email corretamente, deve conter o @");
          txtEmail.requestFocus();
          return false;
        }  
        if(txtEndereco.getText().replace(" ", "").length() < 5){
          JOptionPane.showMessageDialog(rootPane, "Preencha o endereco corretamente");
          txtEndereco.requestFocus();
          return false;
        }  
        if(ftxtTelefone.getText().replace(" ", "").length() < 12){
          JOptionPane.showMessageDialog(rootPane, "Preencha o telefone corretamente");
          ftxtTelefone.requestFocus();
          return false;
        }

        if(pswSenha.getPassword().length != 8){
          JOptionPane.showMessageDialog(rootPane, "Preencha senha corretamente, ela deve conter 8 dígitos");
          pswSenha.requestFocus();
          return false;
        }  
        if(!formataSenhas()){
          JOptionPane.showMessageDialog(rootPane, "Preencha senha corretamente, ela deve conter apenas numeros");
          pswSenha.requestFocus();
          return false;
        }
        if(pswSenhaLogin.getPassword().length != 6){
          JOptionPane.showMessageDialog(rootPane, "Preencha senha de Login corretamente, ela deve conter 6 caracteres");
          pswSenhaLogin.requestFocus();
          return false;
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCadastro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        ftxtCpf = new javax.swing.JFormattedTextField();
        lblNascimento = new javax.swing.JLabel();
        ftxtNascimento = new javax.swing.JFormattedTextField();
        lblSexo = new javax.swing.JLabel();
        cbxSexo = new javax.swing.JComboBox<>();
        lblTelefone = new javax.swing.JLabel();
        ftxtTelefone = new javax.swing.JFormattedTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        pswSenha = new javax.swing.JPasswordField();
        lblTipoConta = new javax.swing.JLabel();
        cbxTipoConta = new javax.swing.JComboBox<>();
        lblTitulo = new javax.swing.JLabel();
        pswSenhaLogin = new javax.swing.JPasswordField();
        lblSenhaLogin = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");

        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCadastro.setToolTipText("pnlCadastroPf");

        lblNome.setText("Nome:");

        lblCpf.setText("CPF:");

        lblNascimento.setText("Nascimento:");

        ftxtNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtNascimentoActionPerformed(evt);
            }
        });

        lblSexo.setText("Sexo:");

        cbxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        lblTelefone.setText("Telefone:");

        lblEmail.setText("E-mail:");

        lblEndereco.setText("Endereço:");

        lblSenha.setText("Senha:");

        pswSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaActionPerformed(evt);
            }
        });

        lblTipoConta.setText("Tipo da conta:");

        cbxTipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poupanca", "Corente" }));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        lblTitulo.setText("Cadastro");
        lblTitulo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        pswSenhaLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaLoginActionPerformed(evt);
            }
        });

        lblSenhaLogin.setText("Senha de Login:");

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(lblTitulo))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblTipoConta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pswSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblEndereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(lblTelefone)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(lblSexo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(lblSenha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblNome)
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(lblCpf)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(lblNascimento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(108, 108, 108)))
                        .addContainerGap())
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblSenhaLogin)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome)
                .addGap(3, 3, 3)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblCpf)
                        .addGap(23, 23, 23))
                    .addComponent(ftxtCpf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNascimento)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(ftxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblSexo)
                        .addGap(19, 19, 19))
                    .addComponent(cbxSexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail)
                .addGap(1, 1, 1)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefone)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(ftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEndereco)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblSenha)
                        .addGap(19, 19, 19))
                    .addComponent(pswSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(lblSenhaLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pswSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoConta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\voltar.png")); // NOI18N
        btnVoltar.setText(" Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        pnlBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\confirmar.png")); // NOI18N
        btnSalvar.setText("Confirmar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        if(validaCampos()){
            try {
                if(ControllerPessoaFisica.insert(ftxtCpf.getText(),txtNome.getText(),formataData.parse(ftxtNascimento.getText()),cbxSexo.getItemAt(cbxSexo.getSelectedIndex()).toCharArray()[0],txtEmail.getText(),ftxtTelefone.getText(),txtEndereco.getText(), senha, senhaLogin)){
                    JOptionPane.showMessageDialog(rootPane,"Cadastro realizado com sucesso");
                    int tipo = 0;
                    switch(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0]){
                        case 'P':
                            tipo = 4;
                            break;
                        case 'C':
                            tipo = 2;                                
                            break;
                    }
                    String s = ControllerConta.cadastraConta(ftxtCpf.getText(),tipo, 'N');
                    if(!s.isEmpty()){
                        JOptionPane.showMessageDialog(rootPane,dadosConta(s));
                        new FrmLogin().setVisible(true);
                        this.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(rootPane,"Erro ao criar conta");
                }
                else
                    JOptionPane.showMessageDialog(rootPane,"Erro ao cadastrar usuário");
            } catch (ParseException ex) {
                Logger.getLogger(FrmCadastroPF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmSelectCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void pswSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaActionPerformed

    private void pswSenhaLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaLoginActionPerformed

    private void ftxtNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtNascimentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxSexo;
    private javax.swing.JComboBox<String> cbxTipoConta;
    private javax.swing.JFormattedTextField ftxtCpf;
    private javax.swing.JFormattedTextField ftxtNascimento;
    private javax.swing.JFormattedTextField ftxtTelefone;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNascimento;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenhaLogin;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipoConta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JPasswordField pswSenhaLogin;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
