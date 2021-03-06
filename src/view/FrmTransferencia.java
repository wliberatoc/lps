/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.classes.Conta;
import model.classes.MovimentacaoBancaria;
import model.dao.ContaDAO;
import model.dao.MovimentacaoBancariaDAO;

/**
 *
 * @author Willian-PC
 */
public class FrmTransferencia extends javax.swing.JFrame {

    /**
     * Creates new form FrmTransferencia
     * @param id
     * @param i
     */
    ContaDAO contaDao = new ContaDAO();
    ArrayList<Conta> conta  = new ArrayList<>();
    ArrayList<Conta> destinatario  = new ArrayList<>();
    int i = 0;
    float valor = 0;
    public FrmTransferencia(int id, int i) {
        initComponents();
        this.i = i;
        try {
            MaskFormatter maskNumConta = new MaskFormatter("#.###-#");
            maskNumConta.install(ftxtNumeroDaConta);
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroPF.class.getName()).log(Level.SEVERE, null, ex);
        }
        conta = contaDao.load(id);
        
    }
    
    public void limpaCampos(){
        ftxtNumeroDaConta.setText("");
        txtAgencia.setText("");
        cbxTipoDaConta.setSelectedIndex(0);
        cbxTipoOperacao.setSelectedIndex(0);
    }
    
    public boolean validaCampos(){
        int tipo  = 0;
        if(cbxTipoDaConta.getSelectedIndex() == 0)
            tipo = 2;
        if(cbxTipoDaConta.getSelectedIndex() == 1)
            tipo = 3;
        if(cbxTipoDaConta.getSelectedIndex() == 2)
            tipo = 4;
        else
            tipo = 5;
        destinatario = contaDao.select2Campos("numreo_da_conta", ftxtNumeroDaConta.getText(),"tipo",tipo);
        if(destinatario.isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Conta informada errado");
            ftxtNumeroDaConta.requestFocus();
            return false;
        }
        if(conta.get(0).getId() == destinatario.get(0).getId()){
            JOptionPane.showMessageDialog(rootPane, "Você não pode transferir para a mesma conta");
            ftxtNumeroDaConta.requestFocus();
        }
        if(!"0001".equals(txtAgencia.getText())){
            JOptionPane.showMessageDialog(rootPane, "Agencia informada errado");
            txtAgencia.requestFocus();
            return false;
        }
        valor = Float.parseFloat(txtValor.getText());
        if(valor < 3 || valor > conta.get(0).getLimiteTeds()){
           JOptionPane.showMessageDialog(rootPane, "O valor informado deve ser um número entre 3 e "+conta.get(0).getLimiteTeds());
           txtValor.requestFocus();
           return false;
        }
        return true;
    }
    
    public boolean salvar(){
        Date hoje = new Date();
        MovimentacaoBancaria mvb = new MovimentacaoBancaria();
        mvb.setIdConta(conta.get(0).getId());
        mvb.setData(hoje);
        mvb.setIdTipoOperacao(3);
        mvb.setDescricao(txtDescricao.getText());
        mvb.setTipoMovimentacao('D');
        MovimentacaoBancariaDAO mvbDao = new MovimentacaoBancariaDAO();
        if(!mvbDao.insert(mvb))
            return false;
        mvb.setIdConta(destinatario.get(0).getId());
        mvb.setTipoMovimentacao('C');
        if(mvbDao.insert(mvb)){
            mvb.setIdConta(conta.get(0).getId());
            mvb.setTipoMovimentacao('S');
            mvb.setDescricao("saldo de "+hoje.getTime());
            mvb.setIdTipoOperacao(0);
            mvbDao.insert(mvb);
            mvb.setId(destinatario.get(0).getId());
            mvbDao.insert(mvb);
            return true;
        }else{
            mvb.setId(conta.get(0).getId());
            mvb.setIdTipoOperacao(2);
            mvb.setDescricao("estorno de "+txtDescricao.getText());
            mvb.setTipoMovimentacao('C');
            return false;
        }            
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Transferencia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N

        lblNumeroDaConta.setText("Número da conta:");

        lblAgencia.setText("Agência:");

        lblTipoDaConta.setText("Tipo da conta:");

        cbxTipoDaConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));
        cbxTipoDaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDaContaActionPerformed(evt);
            }
        });

        lblValor.setText("Valor:");

        lblTipoOperacao.setText("Tipo da Operação:");

        cbxTipoOperacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblDescricao.setText("Descrição:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTipoOperacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNumeroDaConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTipoDaConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblValor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtValor))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblAgencia)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftxtNumeroDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroDaConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoDaConta)
                    .addComponent(cbxTipoDaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoOperacao)
                    .addComponent(cbxTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if(validaCampos()){
            if(salvar()){
                JOptionPane.showMessageDialog(rootPane,"Edição realizada com sucesso");
                new FrmHome(conta.get(0).getUsuario(),i).setVisible(true);
                this.dispose();
            }
            else
            JOptionPane.showMessageDialog(rootPane,"Erro ao editar usuário");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmHome(conta.get(0).getUsuario(),this.i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbxTipoDaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDaContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDaContaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxTipoDaConta;
    private javax.swing.JComboBox<String> cbxTipoOperacao;
    private javax.swing.JFormattedTextField ftxtNumeroDaConta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNumeroDaConta;
    private javax.swing.JLabel lblTipoDaConta;
    private javax.swing.JLabel lblTipoOperacao;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
