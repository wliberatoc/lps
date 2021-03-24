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
/**
 *
 * @author Willian-PC
 */
public final class FrmTransferencia extends javax.swing.JFrame {

    /**
     * Creates new form FrmTransferencia
     * @param id
     * @param i
     */
    Object [] conta  = new Object [11];
    Object [] destinatario  = new Object [11];
    int i = 0;
    float valor = 0;
    public FrmTransferencia(int id, int i) {
        initComponents();
        carregaTipo();
        this.i = i;
        try {
            MaskFormatter maskNumConta = new MaskFormatter("#.###-#");
            maskNumConta.install(ftxtNumeroDaConta);
        } catch (ParseException ex) {
            System.err.println("Erro: "+ex);
        }
        conta = ControllerConta.load(id);
        
    }
    
    public void carregaTipo(){
        ControllerMovimentacaoBancaria.pegaTipos().forEach((tipo) -> {
            if((int)tipo[0] != 1 && (int)tipo[0] != 4 && (int)tipo[0] != 5 && (int)tipo[0] != 6)
                cbxTipoOperacao.addItem(tipo);
        });
    }
    
    public void limpaCampos(){
        ftxtNumeroDaConta.setText("");
        txtAgencia.setText("");
        cbxTipoDaConta.setSelectedIndex(0);
        cbxTipoOperacao.setSelectedIndex(0);
        pswSenha.setText("");
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
        destinatario = ControllerConta.confirmaConta(ftxtNumeroDaConta.getText(), tipo);
        if(destinatario == null){
            JOptionPane.showMessageDialog(rootPane, "Conta informada errado");
            ftxtNumeroDaConta.requestFocus();
            return false;
        }
        if((int)conta[0] == (int)destinatario[0]){
            JOptionPane.showMessageDialog(rootPane, "Você não pode transferir para a mesma conta");
            ftxtNumeroDaConta.requestFocus();
        }
        if(!"0001".equals(txtAgencia.getText())){
            JOptionPane.showMessageDialog(rootPane, "Agencia informada errado");
            txtAgencia.requestFocus();
            return false;
        }
        try{
            if(valor < 3 || valor > (float)conta[7]){
               JOptionPane.showMessageDialog(rootPane, "O valor informado deve ser um número entre 3 e "+(float)conta[7]);
               txtValor.requestFocus();
               return false;
            }
            if(valor > (float)conta[4]){
                JOptionPane.showMessageDialog(rootPane, "Saldo insuficiente");
                txtValor.requestFocus();
                return false;
            }
            String s  =  ""+Arrays.toString(pswSenha.getPassword());
            s = s.replace(" ", "").replace(",", "").replace("[", "").replace("]", "");
            try{
                int senha = Integer.parseInt(s); 
                    if((int)conta[3]%2 == 0){
                        if(ControllerPessoaFisica.confirma((String)conta[10], senha)){
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Senha incorreta ela deve conter 8 números");
                            pswSenha.requestFocus();
                            return false;
                        }   
                    }else if((int)conta[3]%2 == 1) {
                        if(ControllerPessoaJuridica.confirma((String)conta[10], senha)){
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Senha incorreta ela deve conter 8 números");
                            pswSenha.requestFocus();
                            return false;
                        }
                    } 
            }catch (NumberFormatException ex){
               JOptionPane.showMessageDialog(rootPane, "A senha deve contaer apenas numeros");
               pswSenha.requestFocus();
               return false;
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "O valor deve conter apenas números");
            txtValor.requestFocus();
            return false;
        }
        return true;
    }//fim valida campos

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTransferencia = new javax.swing.JPanel();
        ftxtNumeroDaConta = new javax.swing.JFormattedTextField();
        lblNumeroDaConta = new javax.swing.JLabel();
        txtAgencia = new javax.swing.JTextField();
        lblAgencia = new javax.swing.JLabel();
        lblTipoDaConta = new javax.swing.JLabel();
        cbxTipoDaConta = new javax.swing.JComboBox<>();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblTipoOperacao = new javax.swing.JLabel();
        cbxTipoOperacao = new javax.swing.JComboBox<>();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        pswSenha = new javax.swing.JPasswordField();
        lblSenha = new javax.swing.JLabel();
        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTransferencia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Transferencia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N

        lblNumeroDaConta.setText("Número da conta:");

        lblAgencia.setText("Agência:");

        lblTipoDaConta.setText("Tipo da conta:");

        cbxTipoDaConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente Pessoa Física", "Conta Corrente Pessoa Juridica", "Poupança Pessoa Física", "Poupança Pessoa Juridica" }));
        cbxTipoDaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDaContaActionPerformed(evt);
            }
        });

        lblValor.setText("Valor:");

        lblTipoOperacao.setText("Tipo da Operação:");

        lblDescricao.setText("Descrição:");

        pswSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaActionPerformed(evt);
            }
        });

        lblSenha.setText("Senha:");

        javax.swing.GroupLayout pnlTransferenciaLayout = new javax.swing.GroupLayout(pnlTransferencia);
        pnlTransferencia.setLayout(pnlTransferenciaLayout);
        pnlTransferenciaLayout.setHorizontalGroup(
            pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                        .addComponent(lblSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblDescricao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDescricao))
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblNumeroDaConta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblAgencia)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblTipoDaConta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblTipoOperacao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbxTipoOperacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                            .addComponent(lblValor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        pnlTransferenciaLayout.setVerticalGroup(
            pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransferenciaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroDaConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoDaConta)
                    .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoOperacao)
                    .addComponent(cbxTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

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

        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\voltar.png")); // NOI18N
        btnVoltar.setText(" Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTransferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        valor = Float.parseFloat(txtValor.getText());
        if((int)conta[5] == 0)
             valor = (float) (valor * 1.03);
        if(validaCampos()){
            Date hoje = new Date();
            String descricao = txtDescricao.getText();
            int tipo = ControllerMovimentacaoBancaria.pegaIdTipo(cbxTipoOperacao.getItemAt(cbxTipoOperacao.getSelectedIndex()).toString());
            if((int)conta[5] != 0)
                ControllerConta.update((float)conta[4],(int)conta[5]-1,(int)conta[6],(int)conta[0]);
            if(ControllerMovimentacaoBancaria.insert((int)conta[0], 'D', hoje, tipo, descricao, valor)){
                ControllerMovimentacaoBancaria.insert((int)destinatario[0], 'C', hoje, tipo, descricao, valor);
                descricao = "saldo de "+hoje;
                ControllerMovimentacaoBancaria.insert((int)conta[0], 'S', hoje, 1, descricao, ControllerConta.atualizaSaldo((int)conta[0]));
                ControllerMovimentacaoBancaria.insert((int)destinatario[0], 'S', hoje, 1, descricao, ControllerConta.atualizaSaldo((int)destinatario[0]));
                JOptionPane.showMessageDialog(rootPane,"Transferencia realizada com sucesso");
                int confirma = JOptionPane.showConfirmDialog(rootPane, "deseja realizar outra transferencia?","",JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION){
                    limpaCampos();
                    ftxtNumeroDaConta.requestFocus();
                }else{
                    new FrmHome((String)conta[10],i).setVisible(true);
                    this.dispose();
                }  
            }else
                JOptionPane.showMessageDialog(rootPane,"Erro na transferencia");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmHome((String)conta[10],this.i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbxTipoDaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDaContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDaContaActionPerformed

    private void pswSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxTipoDaConta;
    private javax.swing.JComboBox<Object> cbxTipoOperacao;
    private javax.swing.JFormattedTextField ftxtNumeroDaConta;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNumeroDaConta;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTipoDaConta;
    private javax.swing.JLabel lblTipoOperacao;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlTransferencia;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
