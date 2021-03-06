/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerBoleto;
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
public class FrmPagamento extends javax.swing.JFrame {

    /**
     * Creates new form FrmPagamento
     */
    Object [] conta  = new Object [11];
    Object [] boleto  = new Object [6];
    int i = 0;
    float valor = 0;
    public FrmPagamento(int id, int i) {
        initComponents();
        this.i = i;
        conta = ControllerConta.load(id);
        try {
            MaskFormatter maskCodDeBarras = new MaskFormatter("#######.#######.####### # #######");
            maskCodDeBarras.install(ftxtCodigoDeBarras);
        } catch (ParseException ex) {
            System.err.println("Erro: "+ex);
        }
    }
    
    public void limpaCampos(){
        ftxtCodigoDeBarras.setText("");
        txtValor.setText("");
        pswSenha.setText("");
    }
     
    public boolean validaCampos(){
        boleto = ControllerBoleto.select(ftxtCodigoDeBarras.getText());
        if(boleto == null){
            JOptionPane.showMessageDialog(rootPane, "Codigo de barras informado errado");
            ftxtCodigoDeBarras.requestFocus();
            return false;
        }
        if(ControllerBoleto.isVencido((String)boleto[4])) {
            JOptionPane.showMessageDialog(rootPane, "Boleto vencido");
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
            if(valor != (float)boleto[3]){
                JOptionPane.showMessageDialog(rootPane, "O valor informado deve ser o mesmo valor do boleto");
                txtValor.requestFocus();
                return false;
            }
            if((boolean)boleto[5] == true){
                JOptionPane.showMessageDialog(rootPane, "Este boleto ja foi pago");
                ftxtCodigoDeBarras.requestFocus();
                return false;
            } 
            String s  =  ""+Arrays.toString(pswSenha.getPassword());
            s = s.replace(" ", "").replace(",", "").replace("[", "").replace("]", "");
            try{
                int senha = Integer.parseInt(s); 
                    if((int)conta[3]%2 == 0){
                        if(ControllerPessoaFisica.confirma((String)conta[10], senha))
                            return true;
                        else{
                            JOptionPane.showMessageDialog(rootPane, "Senha incorreta ela deve conter 8 números");
                            pswSenha.requestFocus();
                            return false;
                        }   
                    }else if((int)conta[3]%2 == 1) {
                        if(ControllerPessoaJuridica.confirma((String)conta[10], senha))
                            return true;
                        else{
                            JOptionPane.showMessageDialog(rootPane, "Senha incorreta ela deve conter 8 números");
                            pswSenha.requestFocus();
                            return false;
                        }   
                    } 
            }catch (NumberFormatException ex){
               JOptionPane.showMessageDialog(rootPane, "A senha deve conter apenas numeros");
               pswSenha.requestFocus();
               return false;
            }
            if((boolean)boleto[5]){
                JOptionPane.showMessageDialog(rootPane, "Esse boleto ja foi pago");
                return false;
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "O valor deve conter apenas números");
            txtValor.requestFocus();
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

        pnlPagamento = new javax.swing.JPanel();
        ftxtCodigoDeBarras = new javax.swing.JFormattedTextField();
        lblCodigoDeBarras = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        pswSenha = new javax.swing.JPasswordField();
        lblSenha = new javax.swing.JLabel();
        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pagamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N

        lblCodigoDeBarras.setText("Código de barras:");

        lblValor.setText("Valor:");

        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        pswSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswSenhaActionPerformed(evt);
            }
        });

        lblSenha.setText("Senha:");

        javax.swing.GroupLayout pnlPagamentoLayout = new javax.swing.GroupLayout(pnlPagamento);
        pnlPagamento.setLayout(pnlPagamentoLayout);
        pnlPagamentoLayout.setHorizontalGroup(
            pnlPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagamentoLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(pnlPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagamentoLayout.createSequentialGroup()
                        .addComponent(lblCodigoDeBarras)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagamentoLayout.createSequentialGroup()
                        .addComponent(ftxtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(pnlPagamentoLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(pnlPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPagamentoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblSenha))
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPagamentoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblValor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPagamentoLayout.setVerticalGroup(
            pnlPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPagamentoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblCodigoDeBarras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ftxtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVoltar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 132, Short.MAX_VALUE)
                                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pnlPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
            String descricao ="pagamento boleto";
            Date hoje = new Date();
             if((int)conta[5] != 0)
                ControllerConta.update((float)conta[4],(int)conta[5]-1,(int)conta[6],(int)conta[0]);
            if(ControllerMovimentacaoBancaria.insert((int)conta[0], 'D', hoje, 5, descricao,valor)){
                ControllerMovimentacaoBancaria.insert((int)boleto[2], 'C', hoje, 5, descricao, valor);
                descricao = "saldo de "+hoje;
                ControllerMovimentacaoBancaria.insert((int)conta[0], 'S', hoje, 1, descricao, ControllerConta.atualizaSaldo((int)conta[0]));
                ControllerMovimentacaoBancaria.insert((int)boleto[2], 'S', hoje, 1, descricao, ControllerConta.atualizaSaldo((int)boleto[2]));
                ControllerBoleto.update((boolean)boleto[5],(int)boleto[0]);
                JOptionPane.showMessageDialog(rootPane,"Pagamento realizado com sucesso");
                int confirma = JOptionPane.showConfirmDialog(rootPane, "deseja realizar outro pagamento?","",JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION){
                    limpaCampos();
                    ftxtCodigoDeBarras.requestFocus();
                }else{
                    new FrmHome((String)conta[10], i).setVisible(true);
                    this.dispose();
                }
            }else
                JOptionPane.showMessageDialog(rootPane,"Erro no pagamento");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmHome((String)conta[10], i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void pswSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JFormattedTextField ftxtCodigoDeBarras;
    private javax.swing.JLabel lblCodigoDeBarras;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlPagamento;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
