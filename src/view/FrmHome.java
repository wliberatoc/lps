/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import model.classes.Boleto;
import model.classes.Conta;
import model.classes.PessoaFisica;
import model.classes.PessoaJuridica;
import model.dao.BoletoDAO;
import model.dao.ContaDAO;
import model.dao.PessoaFisicaDAO;
import model.dao.PessoaJuridicaDAO;


/**
 *
 * @author Willian-PC
 */
public class FrmHome extends javax.swing.JFrame {

    /**
     * Creates new form frmHome
     */
    private final SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    ContaDAO contaDao = new ContaDAO();
    ArrayList<Conta> conta  = new ArrayList<>();
    PessoaJuridicaDAO pj = new PessoaJuridicaDAO();
    ArrayList<PessoaJuridica> clienteJ  = new ArrayList<>(); 
    PessoaFisicaDAO pf = new PessoaFisicaDAO();
    ArrayList<PessoaFisica> clienteF  = new ArrayList<>(); 
    String data = "";
    String codB = "";
    int i = 0;
    
    public FrmHome(String use, int indice) {
        initComponents();
        i = indice;
        conta = contaDao.select("usuario", use);
        btnOutraConta.setText("Mudar conta");
        if(conta.get(i).getTipo() < 4)
            lblTipoConta.setText("Saldo Conta Corrente:");
        else
            lblTipoConta.setText("Saldo Poupança:");
        if(conta.size() == 1)
            btnOutraConta.setText("Criar conta");
        txtSaldo.setText(""+conta.get(i).getSaldo());
        if(use.length() < 18){
            clienteF = pf.select("cpf",use);
            lblNome.setText(clienteF.get(0).getNome());
        }            
        else{
            clienteJ = pj.select("cnpj",use);
            lblNome.setText(clienteJ.get(0).getNome());
        }   
    }  
    
    public void mudarDeConta(){
        new FrmHome(conta.get(i).getUsuario(),1).setVisible(true);
        this.dispose();
    }
    
    public boolean criaConta(){
        Conta newConta = new Conta();
        newConta.setNumeroDaConta(conta.get(i).getNumeroDaConta());
        newConta.setUsuario(conta.get(i).getUsuario());
        newConta.setAgencia("0001");
        Date hoje = new Date();
        newConta.setAbertura(hoje);
        if(conta.get(i).getTipo() == 2){
            newConta.setTipo(4);
            newConta.setQtdTeds(7);
            newConta.setLimiteTeds(1000);
            newConta.setQtdSaques(15);
            newConta.setLimiteSaques(1500);
        }
        if(conta.get(i).getTipo() == 4){
            newConta.setTipo(2);
            newConta.setQtdTeds(10);
            newConta.setLimiteTeds(1500);
            newConta.setQtdSaques(20);
            newConta.setLimiteSaques(2000);
        }
        if(conta.get(i).getTipo() == 3){
            newConta.setTipo(5);
            newConta.setQtdTeds(15);
            newConta.setLimiteTeds(3000);
            newConta.setQtdSaques(50);
            newConta.setLimiteSaques(5000);
        }
        if(conta.get(i).getTipo() == 5){
            newConta.setTipo(3);
            newConta.setQtdTeds(10);
            newConta.setLimiteTeds(1500);
            newConta.setQtdSaques(20);
            newConta.setLimiteSaques(2000);            
        }
        return contaDao.insert(newConta);
    }
    
    public void editar(){
        if(conta.get(i).getTipo() % 2 == 0){
            new FrmEditPF(clienteF.get(0)).setVisible(true);
            this.dispose();
        }else{
            new FrmEditPJ(clienteJ.get(0)).setVisible(true);
            this.dispose();
        }
    }
    
    public void excluir(){
        int confirma = JOptionPane.showConfirmDialog(rootPane, "Confirma que deseja EXCLUIR SEU CADASTROS?","Confimação",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){ 
            if(conta.get(i).getTipo() % 2 == 0){
                if(pf.delete(clienteF.get(0).getId()) && contaDao.deleteAll(clienteF.get(0).getCpf())){
                    JOptionPane.showMessageDialog(rootPane, "Conta Excluida");
                    new FrmLogin().setVisible(true);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Erro ao tentar Excluir");
            }else{
                if(pj.delete(clienteJ.get(0).getId()) && contaDao.deleteAll(clienteJ.get(0).getCnpj())){
                    JOptionPane.showMessageDialog(rootPane, "Conta Excluida");
                    new FrmLogin().setVisible(true);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Erro ao tentar Excluir");
            }
        }
    }
    
    public String criaData(){
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH)+1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        if(mes == 12 && dia > 28){
            mes = 01;
            ano++;
            dia = (dia+3)-30;
            return ""+dia+"/"+mes+"/"+ano;
        }            
        if((mes == 2 && (ano % 400 == 0)) && dia > 26){
            mes++;
            dia = (dia+3)-29;
            return ""+dia+"/"+mes+"/"+ano;
        }
        if((mes == 2 && (ano % 400 != 0)) && dia > 25){
            mes++;
            dia = (dia+3)-28;
            return ""+dia+"/"+mes+"/"+ano;
        }  
        if(((mes < 8 && mes%2 == 0)||(mes > 7 && mes%2 != 0)) && dia > 27){
            mes++;
            dia = (dia+3)-30;
            return ""+dia+"/"+mes+"/"+ano;
        }
        if(dia > 28){
            mes++;
            dia = (dia+3)-31;
            return ""+dia+"/"+mes+"/"+ano;
        }         
        else{
            dia = dia+3;
            return ""+dia+"/"+mes+"/"+ano;
        }
    }//fim cria data
    
    public boolean geraBoleto(){
        BoletoDAO boletoDAO = new BoletoDAO();
        Boleto boleto = new Boleto();
        codB = geraCodigoBarras();
        boleto.setCodigoDeBarras(codB);
        boleto.setIdConta(conta.get(i).getId());
        try{
            boleto.setValor(Float.parseFloat(txtValor.getText()));
            if(boleto.getValor() < 3 || boleto.getValor() > 9999999){
                JOptionPane.showMessageDialog(rootPane, "Valor informado deve ser maior que 3 e com no maximo 7 digitos");
                txtValor.requestFocus();
                return false;
            }
            data = criaData();
            try {
                boleto.setDataDeVencimento(formataData.parse(data));
                return boletoDAO.insert(boleto);
            } catch (ParseException ex) {
                System.err.println("Erro "+ex);
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Valor informado errado, digite um numero real positivo");
            txtValor.requestFocus();
        }
        return false; 
    }
    
    public String geraCodigoBarras(){
        Random random = new Random();
        int dv = random.nextInt(9);
        String s = "000"+conta.get(i).getNumeroDaConta().replace(".", "").replace("-", "")
                +".000"+conta.get(i).getAgencia()+".000000"+conta.get(i).getTipo()+" "+dv+" ";
        int p = 7-txtValor.getText().replace(".","").length();
        for(int k = 0; k < 7; k++){
            if(k == p){
                s += txtValor.getText().replace(".","");
                break;
            }else
                s += "0";
        }
        return s;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHome = new javax.swing.JPanel();
        txtSaldo = new javax.swing.JTextField();
        btnOutraConta = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        lblTipoConta = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        brnInfo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbxVisualizar = new javax.swing.JComboBox<>();
        btnVisualizar = new javax.swing.JButton();
        cbxRealizar = new javax.swing.JComboBox<>();
        btnREalizar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        pnlDeposito = new javax.swing.JPanel();
        btnDepositar = new javax.swing.JButton();
        lblValor = new javax.swing.JLabel();
        lblValidade = new javax.swing.JLabel();
        txtCodigoDeBarras = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtVencimento = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "HOME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial Black", 0, 20))); // NOI18N

        txtSaldo.setEditable(false);

        btnOutraConta.setText("defalut");
        btnOutraConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutraContaActionPerformed(evt);
            }
        });

        lblNome.setText("default");

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblTipoConta.setText("default");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        brnInfo.setText("info");
        brnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addComponent(btnOutraConta)
                        .addGap(63, 63, 63)
                        .addComponent(btnExcluir))
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipoConta)
                        .addGap(18, 18, 18)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(brnInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnEditar)))
                .addGap(80, 80, 80))
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOutraConta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(brnInfo))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbxVisualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saldo", "Extrato" }));

        btnVisualizar.setText("Visualizar");

        cbxRealizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagamento", "Transferencia" }));

        btnREalizar.setText("Realizar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnREalizar)))
                .addGap(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizar)
                    .addComponent(btnREalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        pnlDeposito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnDepositar.setText("Depositar");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        lblValor.setText("Valor:");

        lblValidade.setText("Validade:");

        txtCodigoDeBarras.setEditable(false);

        jLabel1.setText("Código de Barras");

        txtVencimento.setEditable(false);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDepositoLayout = new javax.swing.GroupLayout(pnlDeposito);
        pnlDeposito.setLayout(pnlDepositoLayout);
        pnlDepositoLayout.setHorizontalGroup(
            pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDepositoLayout.createSequentialGroup()
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDepositoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(pnlDepositoLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lblValidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDepositar)
                    .addGroup(pnlDepositoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnLimpar)))
                .addGap(103, 103, 103))
        );
        pnlDepositoLayout.setVerticalGroup(
            pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepositar)
                    .addComponent(lblValor)
                    .addComponent(lblValidade)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnLimpar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(btnSair)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        // depositar
        if(geraBoleto()){
            JOptionPane.showMessageDialog(rootPane, "Boleto gerado com sucesso");
            txtVencimento.setText(data);
            txtCodigoDeBarras.setText(codB);
        }else
            JOptionPane.showMessageDialog(rootPane, "Erro na geração do boleto");
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // editar
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // excluir
        excluir();            
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //sair
        new FrmLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnOutraContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutraContaActionPerformed
        // criar
        if(btnOutraConta.getText().equals("Criar conta"))
            if(criaConta()){
                JOptionPane.showMessageDialog(rootPane,"Conta criada com sucesso");
                btnOutraConta.setText("Mudar conta");
            }
            else
                JOptionPane.showMessageDialog(rootPane,"Erro ao criar conta");
        else{// mudar
            mudarDeConta();
        }   
    }//GEN-LAST:event_btnOutraContaActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // limpar
        txtValor.setText("");
        txtVencimento.setText("");
        txtCodigoDeBarras.setText("");
        data = "";
        codB = "";
    }//GEN-LAST:event_btnLimparActionPerformed

    private void brnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnInfoActionPerformed
        // info
        String info = "Nº conta: "+conta.get(i).getNumeroDaConta()+"\nAgência: "+conta.get(i).getAgencia();
        if(conta.get(i).getTipo() < 4)
            info += "\nConta Corrente";
        else
            info += "\nConta Poupança";
        JOptionPane.showMessageDialog(rootPane,info);
    }//GEN-LAST:event_brnInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnInfo;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnOutraConta;
    private javax.swing.JButton btnREalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cbxRealizar;
    private javax.swing.JComboBox<String> cbxVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTipoConta;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlDeposito;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JTextField txtCodigoDeBarras;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVencimento;
    // End of variables declaration//GEN-END:variables
}
