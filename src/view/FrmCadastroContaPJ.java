/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.classes.ContaPJ;
import model.dao.ContaPJDAO;

/**
 *
 * @author Willian-PC
 */
public class FrmCadastroContaPJ extends javax.swing.JFrame {

    /**
     * Creates new form frmCadastroContaPJ
     */
    private ArrayList<ContaPJ> arrayConta = new ArrayList();
    private ContaPJ conta = new ContaPJ(); 
    private ContaPJDAO contad = new ContaPJDAO(); 
    public FrmCadastroContaPJ() {
        initComponents();
        try {
            MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
            habilitaCampos(false);
            maskCnpj.install(ftxtCnpjTitular);
            carregaConta();
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroContaPJ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void habilitaCampos(boolean flag){
        ftxtCnpjTitular.enable(flag);
        cbxTipoConta.enableInputMethods(flag);
    }
    public void limpaCampos(){
        txtNumConta.setText("");
        ftxtCnpjTitular.setText("");
        txtAgencia.setText("");
        txtTeds.setText("");
        txtLimiteTed.setText("");
        txtSaques.setText("");
        txtLimiteSaque.setText("");
        cbxTipoConta.setSelectedIndex(0);
    }
    
    public void preencheCampos(){
        Random random = new Random();
        int n1 = random.nextInt(9);
        int n2 = random.nextInt(9);
        int n3 = random.nextInt(9);
        int n4 = random.nextInt(9);
        int dv = random.nextInt(9);
        String s = ""+n1+"."+n2+n3+n4+"-"+dv;
        txtNumConta.setText(s);
        txtAgencia.setText("0001");
        if(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0] == 'P'){
            txtTeds.setText("15");
            txtLimiteTed.setText("3000");
            txtSaques.setText("50");
            txtLimiteSaque.setText("5000");
        }
        if(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0] == 'C'){
            txtTeds.setText("80");
            txtLimiteTed.setText("20000");
            txtSaques.setText("70");
            txtLimiteSaque.setText("10000");
        }
    }
    
    public void salvarConta(){
        conta.setCnpjTitular(ftxtCnpjTitular.getText());
        conta.setAgencia(txtAgencia.getText());
        conta.setTipo(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0]);
        conta.setTeds(Integer.parseInt(txtTeds.getText()));
        conta.setSaques(Integer.parseInt(txtSaques.getText()));
        conta.setLimiteSaques(Float.parseFloat(txtLimiteSaque.getText()));
        conta.setLimiteTeds(Float.parseFloat(txtLimiteTed.getText()));  
        
    }
    public void carregaConta(){
        arrayConta = contad.selectAll();
        String [] colunas = {"CNPJ Titular", "Numero da conta", "Agencia", "Tipo", "Data de Abertura", "Teds", "Saques", "limite Saque", "Limite Ted"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        
        for(int i=0; i<arrayConta.size(); i++){
            Object [] linha = {arrayConta.get(i).getCnpjTitular(),arrayConta.get(i).getNumeroDaConta(),arrayConta.get(i).getAgencia(),arrayConta.get(i).getTipo(),arrayConta.get(i).getAbertura(),arrayConta.get(i).getTeds(),arrayConta.get(i).getSaques(),arrayConta.get(i).getLimiteSaques(),arrayConta.get(i).getLimiteTeds()};
            model.addRow(linha);
        }
        tblContas.setModel(model);
    }
    public boolean validaCampos(){    
        if(ftxtCnpjTitular.getText().replace(" ", "").length() < 18){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CNPJ corretamente");
            ftxtCnpjTitular.requestFocus();
            return false;
        }  
        return true;
    }
    
    public void editar(){
        String numC;
        if(tblContas.getSelectedRow() != -1){
            numC = tblContas.getValueAt(tblContas.getSelectedRow(), 1).toString();
            conta = buscarConta(numC);
            preencheCamposEdit();
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para editar");
    }
    
    public void excluir(){
        String numC;
        if(tblContas.getSelectedRow() != -1){
            numC = tblContas.getValueAt(tblContas.getSelectedRow(), 1).toString();
            conta = buscarConta(numC);
            preencheCamposEdit();
            if(JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir essa conta?","Confimação",JOptionPane.YES_NO_OPTION)== 0){
                arrayConta.remove(conta);
                carregaConta();
            }
            else{
                limpaCampos();
            }
            
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para excluir");
    }
    
    public ContaPJ buscarConta(String numC){
        for(int i=0; i<arrayConta.size(); i++){
            if(arrayConta.get(i).getNumeroDaConta() == numC)
                return arrayConta.get(i);  
        }
        return null;
    }
    
    public void preencheCamposEdit(){
        ftxtCnpjTitular.setText(conta.getCnpjTitular());
        txtNumConta.setText(conta.getNumeroDaConta());
        txtAgencia.setText(conta.getAgencia());
        if(conta.getTipo() == 'P')
            cbxTipoConta.setSelectedIndex(0);
        if(conta.getTipo() == 'C')
            cbxTipoConta.setSelectedIndex(1);
        txtTeds.setText(String.valueOf(conta.getTeds()));
        txtLimiteTed.setText(String.valueOf(conta.getLimiteTeds()));
        txtSaques.setText(String.valueOf(conta.getSaques()));
        txtLimiteSaque.setText(String.valueOf(conta.getLimiteSaques()));    
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBotoes = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        pnlCadastro = new javax.swing.JPanel();
        lblNumConta = new javax.swing.JLabel();
        txtNumConta = new javax.swing.JTextField();
        lblCnpjTituar = new javax.swing.JLabel();
        lblAgencia = new javax.swing.JLabel();
        txtAgencia = new javax.swing.JTextField();
        lblTeds = new javax.swing.JLabel();
        txtTeds = new javax.swing.JTextField();
        lblTipoConta = new javax.swing.JLabel();
        cbxTipoConta = new javax.swing.JComboBox<>();
        txtSaques = new javax.swing.JTextField();
        lblSaques = new javax.swing.JLabel();
        lblLimiteSaque = new javax.swing.JLabel();
        txtLimiteSaque = new javax.swing.JTextField();
        lblLimiteTed = new javax.swing.JLabel();
        txtLimiteTed = new javax.swing.JTextField();
        ftxtCnpjTitular = new javax.swing.JFormattedTextField();
        pnlTabela = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContas = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        lblTitulo.setText("Cadastro");

        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\confirmar.png")); // NOI18N
        btnSalvar.setText("Confirmar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitulo)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                        .addComponent(btnCancelar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnSalvar)
                        .addGap(33, 33, 33))))
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluir)
                        .addComponent(btnEditar))
                    .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNumConta.setText("Número da conta:");

        txtNumConta.setEditable(false);

        lblCnpjTituar.setText("CNPJ titular:");

        lblAgencia.setText("Agência:");

        txtAgencia.setEditable(false);

        lblTeds.setText("Teds:");

        txtTeds.setEditable(false);

        lblTipoConta.setText("Tipo da conta:");

        cbxTipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poupanca", "Corente" }));
        cbxTipoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoContaActionPerformed(evt);
            }
        });

        txtSaques.setEditable(false);

        lblSaques.setText("Saques:");

        lblLimiteSaque.setText("Limite Saque:");

        txtLimiteSaque.setEditable(false);

        lblLimiteTed.setText("LimiteTed:");

        txtLimiteTed.setEditable(false);

        ftxtCnpjTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtCnpjTitularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblNumConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumConta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblAgencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCadastroLayout.createSequentialGroup()
                            .addComponent(lblTipoConta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCnpjTituar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ftxtCnpjTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCadastroLayout.createSequentialGroup()
                            .addComponent(lblTeds)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTeds, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblLimiteTed)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLimiteTed, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblSaques)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSaques, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblLimiteSaque)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLimiteSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCnpjTituar)
                    .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoConta)
                    .addComponent(ftxtCnpjTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgencia)
                    .addComponent(txtNumConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTeds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTeds)
                    .addComponent(lblSaques)
                    .addComponent(lblLimiteSaque)
                    .addComponent(txtLimiteSaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLimiteTed)
                    .addComponent(txtLimiteTed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTabela.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblContas);

        javax.swing.GroupLayout pnlTabelaLayout = new javax.swing.GroupLayout(pnlTabela);
        pnlTabela.setLayout(pnlTabelaLayout);
        pnlTabelaLayout.setHorizontalGroup(
            pnlTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlTabelaLayout.setVerticalGroup(
            pnlTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlTabela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        if(validaCampos()){
            preencheCampos();
            int confirma = 0;
            confirma = JOptionPane.showConfirmDialog(rootPane, "Confirma que deseja fazer essa edição?","Confimação",JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION){
                salvarConta();
                carregaConta();
                limpaCampos();
            }
            else
                limpaCampos();     
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cbxTipoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoContaActionPerformed

    private void ftxtCnpjTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtCnpjTitularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtCnpjTitularActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmSelectCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // editar
        editar();        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        //Excluir
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxTipoConta;
    private javax.swing.JFormattedTextField ftxtCnpjTitular;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblCnpjTituar;
    private javax.swing.JLabel lblLimiteSaque;
    private javax.swing.JLabel lblLimiteTed;
    private javax.swing.JLabel lblNumConta;
    private javax.swing.JLabel lblSaques;
    private javax.swing.JLabel lblTeds;
    private javax.swing.JLabel lblTipoConta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlTabela;
    private javax.swing.JTable tblContas;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtLimiteSaque;
    private javax.swing.JTextField txtLimiteTed;
    private javax.swing.JTextField txtNumConta;
    private javax.swing.JTextField txtSaques;
    private javax.swing.JTextField txtTeds;
    // End of variables declaration//GEN-END:variables
}
