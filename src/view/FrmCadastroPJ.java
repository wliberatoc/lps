/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.classes.ContaPJ;
import model.classes.PessoaJuridica;
import model.dao.ContaPJDAO;
import model.dao.PessoaJuridicaDAO;

/**
 *
 * @author Willian-PC
 */
public class FrmCadastroPJ extends javax.swing.JFrame {

    /**
     * Creates new form frmCadastroPJ
     */
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    private ContaPJ conta = new ContaPJ();
    private String senhaLogin;
    private int senha;
    
    public FrmCadastroPJ() {
        initComponents();
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
            MaskFormatter maskTelefone = new MaskFormatter("(##)# ####-####");
             
            maskData.install(ftxtFundacao);
            maskCnpj.install(ftxtCnpj);
            maskTelefone.install(ftxtTelefone);
            
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroPJ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        ftxtCnpj.setText("");
        ftxtFundacao.setText("");
        ftxtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        pswSenha.setText("");
        cbxTipoConta.setSelectedIndex(0);
    }
    
    public boolean verificaData(String data){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int ano, mes, dia;
        dia = Integer.parseInt(data.substring(0,2));
        mes = Integer.parseInt(data.substring(3,5));
        ano = Integer.parseInt(data.substring(6,10));
        if(ano < 1694 || ano > year)
            return false;
        if(mes > 12 || mes < 1)
            return false;
        if((mes == 2 && (ano % 400 == 0)) && dia > 29)
            return false;
        if((mes == 2 && (ano % 400 != 0)) && dia > 28)
            return false;
        if((mes < 8 && mes%2 == 0) && dia > 30)
            return false;
        if((mes > 7 && mes%2 != 0) && dia > 30)
            return false;
        if(dia < 1 || dia > 31)
            return false;
        return true;
    }//fim verifica data
    
    public boolean verificarCNPJ(String cnpj){
        int dig1=0, dig2=0, calc1=0, calc2=0, aux=1;
        int [] arrayNumsCalc = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        dig1 = Integer.parseInt(cnpj.substring(16,17));
        dig2 = Integer.parseInt(cnpj.substring(17,18));
        cnpj = cnpj.substring(0,2) + cnpj.substring(3,6) + cnpj.substring(7,10) + cnpj.substring(11,15);
        for(int i=0; i<cnpj.length(); i++){
            calc1 += Integer.parseInt(cnpj.substring(i, i+1)) * arrayNumsCalc[aux];
            if(aux < 12)
                calc2 += Integer.parseInt(cnpj.substring(aux, aux+1)) * arrayNumsCalc[aux];
            aux++;
        }
        calc1 = calc1% 11;
        if(calc1 < 2)
            calc1 = 0;
        else
            calc1 = 11 - calc1;
        
        calc2 += (dig1 * 2) + (Integer.parseInt(cnpj.substring(0, 1)) * 6 );
        calc2 = calc2% 11;
        if(calc2 < 2)
            calc2 = 0;
        else
            calc2 = 11 - calc2;
        if(calc1 == dig1 && calc2 == dig2)
            return true;
        return false;
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
    
    public boolean salvar(){
        try {
            PessoaJuridica pj = new PessoaJuridica();
            pj.setCnpj(ftxtCnpj.getText());
            pj.setNome(txtNome.getText());
            pj.setFundacao(formataData.parse(ftxtFundacao.getText()));
            pj.setEmail(txtEmail.getText());
            pj.setTelefone(ftxtTelefone.getText());
            pj.setEndereco(txtEndereco.getText());
            pj.setSenha(senha);
            pj.setSenhaLogin(senhaLogin);
            PessoaJuridicaDAO cliente = new PessoaJuridicaDAO();
            return cliente.insert(pj);        
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroPF.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean criaConta(){
        Random random = new Random();
        int n1 = random.nextInt(9);
        int n2 = random.nextInt(9);
        int n3 = random.nextInt(9);
        int n4 = random.nextInt(9);
        int dv = random.nextInt(9);
        String s = ""+n1+"."+n2+n3+n4+"-"+dv;
        conta.setNumeroDaConta(s);
        conta.setCnpjTitular(ftxtCnpj.getText());
        conta.setAgencia("0001");
        conta.setTipo(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0]);
        Date hoje = new Date();
        conta.setAbertura(hoje);
        if(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0] == 'P'){
            conta.setTeds(15);
            conta.setLimiteTeds(3000);
            conta.setSaques(50);
            conta.setLimiteSaques(5000);
        }
        if(cbxTipoConta.getItemAt(cbxTipoConta.getSelectedIndex()).toCharArray()[0] == 'C'){
            conta.setTeds(10);
            conta.setLimiteTeds(1500);
            conta.setSaques(20);
            conta.setLimiteSaques(2000);
        }
        ContaPJDAO contaDAO = new ContaPJDAO();
        return contaDAO.insert(conta);
    }
    
    public String dadosConta(){
        String s;
        String numConta = "Nº conta: "+conta.getNumeroDaConta();
        String agencia = "\nAgência: "+conta.getAgencia();
        String tipo = "\nConta ";
        if(conta.getTipo() == 'P')
            tipo += "Poupança";
        if(conta.getTipo() == 'C')
            tipo += "Corrente";
        String cpf = "\nCNPJ: "+conta.getCnpjTitular();
        s = numConta + agencia + tipo + cpf;     
        return s;
    }
    
    public boolean validaCampos(){
        if(!txtNome.getText().replace(" ", "").matches("[A-Za-z]{3,}")){
            JOptionPane.showMessageDialog(rootPane, "Preencha o nome corretamente");
            txtNome.requestFocus();
            return false;
        }
        if(ftxtCnpj.getText().replace(" ", "").length() < 18){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CNPJ corretamente");
            ftxtCnpj.requestFocus();
            return false;
        }
        if(!verificarCNPJ(ftxtCnpj.getText())){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CNPJ corretamente");
            ftxtCnpj.requestFocus();
            return false;
        }
        if(ftxtFundacao.getText().replace(" ", "").length() < 10){
            JOptionPane.showMessageDialog(rootPane, "Preencha a data de Fundação corretamente");
            ftxtFundacao.requestFocus();
            return false;
        } 
        if(!verificaData(ftxtFundacao.getText())){
            JOptionPane.showMessageDialog(rootPane, "Preencha a data de Fundação corretamente");
            ftxtFundacao.requestFocus();
            return false;
        }
        if(ftxtTelefone.getText().replace(" ", "").length() < 12){
            JOptionPane.showMessageDialog(rootPane, "Preencha o telefone corretamente");
            ftxtTelefone.requestFocus();
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
            JOptionPane.showMessageDialog(rootPane, "Preencha senha corretamente, ela deve conter 8 dígitos");
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
        ftxtCnpj = new javax.swing.JFormattedTextField();
        lblFundacao = new javax.swing.JLabel();
        ftxtFundacao = new javax.swing.JFormattedTextField();
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
        lblSenhaLogin = new javax.swing.JLabel();
        pswSenhaLogin = new javax.swing.JPasswordField();
        pnlBotoes1 = new javax.swing.JPanel();
        btnCancelar2 = new javax.swing.JButton();
        btnSalvar1 = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");

        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCadastro.setToolTipText("frmCadastroPF");

        lblNome.setText("Nome:");

        lblCpf.setText("CNPJ:");

        ftxtCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtCnpjActionPerformed(evt);
            }
        });

        lblFundacao.setText("Ano de fundação:");

        lblTelefone.setText("Telefone:");

        lblEmail.setText("E-mail:");

        lblEndereco.setText("Endereço:");

        lblSenha.setText("Senha:");

        lblTipoConta.setText("Tipo da conta:");

        cbxTipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poupanca", "Corente" }));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        lblTitulo.setText("Cadastro");

        lblSenhaLogin.setText("Senha de Login:");

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(lblTitulo))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCpf))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(ftxtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFundacao))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblEndereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblTelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoConta)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSenha))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(ftxtFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNome))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSenhaLogin))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(pswSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(11, 11, 11)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftxtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblFundacao)
                .addGap(1, 1, 1)
                .addComponent(ftxtFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefone)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(ftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblEndereco))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSenha)
                .addGap(5, 5, 5)
                .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblSenhaLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblTipoConta)
                .addGap(2, 2, 2)
                .addComponent(cbxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pnlBotoes1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar2.setText("Cancelar");
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        btnSalvar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\confirmar.png")); // NOI18N
        btnSalvar1.setText("Confirmar");
        btnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoes1Layout = new javax.swing.GroupLayout(pnlBotoes1);
        pnlBotoes1.setLayout(pnlBotoes1Layout);
        pnlBotoes1Layout.setHorizontalGroup(
            pnlBotoes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoes1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar1)
                .addContainerGap())
        );
        pnlBotoes1Layout.setVerticalGroup(
            pnlBotoes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoes1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlBotoes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar1)
                    .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoltar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlBotoes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ftxtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtCnpjActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        if(validaCampos()){
            if(salvar()){
                if(criaConta()){
                    JOptionPane.showMessageDialog(rootPane,dadosConta());
                    new FrmLogin().setVisible(true);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(rootPane,"Erro ao criar conta");
            }
            else
                JOptionPane.showMessageDialog(rootPane,"Erro ao cadastrar usuário");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmSelectCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxTipoConta;
    private javax.swing.JFormattedTextField ftxtCnpj;
    private javax.swing.JFormattedTextField ftxtFundacao;
    private javax.swing.JFormattedTextField ftxtTelefone;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblFundacao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenhaLogin;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipoConta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes1;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JPasswordField pswSenhaLogin;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
