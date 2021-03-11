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
    
    public FrmHome(String use, int i) {
        initComponents();
        this.i = i;
        conta = contaDao.select("usuario", use);
        conta.get(i).setSaldo(conta.get(i).atualizaSaldo()); 
        contaDao.update(conta.get(i));
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
        switch (conta.get(i).getTipo()){
            case 2:
                newConta.setTipo(4);
                newConta.setQtdTeds(7);
                newConta.setLimiteTeds(1000);
                newConta.setQtdSaques(15);
                newConta.setLimiteSaques(1500);
                break;
            case 4:
                newConta.setTipo(2);
                newConta.setQtdTeds(10);
                newConta.setLimiteTeds(1500);
                newConta.setQtdSaques(20);
                newConta.setLimiteSaques(2000);
                break;
            case 3:
                newConta.setTipo(5);
                newConta.setQtdTeds(15);
                newConta.setLimiteTeds(3000);
                newConta.setQtdSaques(50);
                newConta.setLimiteSaques(5000);
                break;
            case 5:
                newConta.setTipo(3);
                newConta.setQtdTeds(10);
                newConta.setLimiteTeds(1500);
                newConta.setQtdSaques(20);
                newConta.setLimiteSaques(2000); 
                break;
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
            }catch (ParseException ex) {
                System.err.println("Erro "+ex);
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Valor informado errado, digite um numero real positivo");
            txtValor.requestFocus();
        }
        return false; 
    }
    
    public String geraCodigoBarras(){
        BoletoDAO boletoDAO = new BoletoDAO();
        Random random = new Random();
        int dv = random.nextInt(9);
        String s = "00"+conta.get(i).getNumeroDaConta().replace(".", "").replace("-", "")
                +"."+conta.get(i).getTipo()+"00"+conta.get(i).getAgencia()+".";
        for(int j = 0; j < 7; j++){
            int dig = random.nextInt(9);
            s += dig;
        }
        s += " "+dv+" ";
        int p = 7-txtValor.getText().replace(".","").length();
        for(int k = 0; k < 7; k++){
            if(k == p){
                s += txtValor.getText().replace(".","");
                break;
            }else
                s += "0";
        }
        if(boletoDAO.select("codigo_de_barras", s).isEmpty())
            return s;
        else
            geraCodigoBarras();
        return null;
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
        btnSaque = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        pnlDeposito = new javax.swing.JPanel();
        btnGerarBoleto = new javax.swing.JButton();
        lblValor = new javax.swing.JLabel();
        lblValidade = new javax.swing.JLabel();
        txtCodigoDeBarras = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtVencimento = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "HOME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial Black", 0, 20))); // NOI18N
        pnlHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtSaldo.setEditable(false);

        btnOutraConta.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\novo.jpeg")); // NOI18N
        btnOutraConta.setText("defalut");
        btnOutraConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutraContaActionPerformed(evt);
            }
        });

        lblNome.setText("default");

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\excluir.jpeg")); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblTipoConta.setText("default");

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\editar.jpeg")); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        brnInfo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\info.jpeg")); // NOI18N
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
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipoConta)
                        .addGap(18, 18, 18)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addComponent(btnOutraConta)
                        .addGap(28, 28, 28)
                        .addComponent(brnInfo)
                        .addGap(33, 33, 33)
                        .addComponent(btnExcluir)
                        .addGap(33, 33, 33)
                        .addComponent(btnEditar)
                        .addGap(55, 55, 55))))
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
        cbxVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxVisualizarActionPerformed(evt);
            }
        });

        btnVisualizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\visualizar.png")); // NOI18N
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        cbxRealizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagamento", "Transferencia" }));

        btnREalizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\realizar.png")); // NOI18N
        btnREalizar.setText("Realizar");
        btnREalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREalizarActionPerformed(evt);
            }
        });

        btnSaque.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\saque.png")); // NOI18N
        btnSaque.setText("Saque");
        btnSaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaqueActionPerformed(evt);
            }
        });

        btnDepositar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\deposito.png")); // NOI18N
        btnDepositar.setText("Depositar");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaque)
                    .addComponent(btnDepositar))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizar))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnREalizar)))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cbxVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(cbxRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnREalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\sair.png")); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        pnlDeposito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGerarBoleto.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\boleto.png")); // NOI18N
        btnGerarBoleto.setText("Gerar Boleto");
        btnGerarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarBoletoActionPerformed(evt);
            }
        });

        lblValor.setText("Valor:");

        lblValidade.setText("Validade:");

        txtCodigoDeBarras.setEditable(false);

        jLabel1.setText("Código de Barras");

        txtVencimento.setEditable(false);

        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\limpar.png")); // NOI18N
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
                        .addGap(79, 79, 79)
                        .addComponent(lblValidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnGerarBoleto))
                    .addGroup(pnlDepositoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDepositoLayout.setVerticalGroup(
            pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerarBoleto)
                    .addComponent(lblValor)
                    .addComponent(lblValidade)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(262, 262, 262))
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

    private void btnGerarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarBoletoActionPerformed
        // GeraBoleto
        if(geraBoleto()){
            JOptionPane.showMessageDialog(rootPane, "Boleto gerado com sucesso");
            txtVencimento.setText(data);
            txtCodigoDeBarras.setText(codB);
        }else
            JOptionPane.showMessageDialog(rootPane, "Erro na geração do boleto");
    }//GEN-LAST:event_btnGerarBoletoActionPerformed

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

    private void btnREalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREalizarActionPerformed
        // transferencia
        if(cbxRealizar.getSelectedIndex() == 1){
            new FrmTransferencia(conta.get(i).getId(),i).setVisible(true);
            this.dispose();
        }else{//pagamento
            new FrmPagamento(conta.get(i).getId(),i).setVisible(true);
            this.dispose();
        }
            
        
    }//GEN-LAST:event_btnREalizarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        //saldo
        if(cbxVisualizar.getSelectedIndex() == 0){
            Calendar cal = Calendar.getInstance();
            int ano = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH)+1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            String d = ""+ano+"-"+mes+"-"+dia;
            new FrmVisualizar(conta.get(i).getId(),i,d,d).setVisible(true);
            this.dispose();
        }else{//extrato
            new FrmSelecionarData(conta.get(i).getId(),i,'E').setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnSaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaqueActionPerformed
        //Sacar
        new FrmSaque(conta.get(i).getId(),i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSaqueActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        //Depositar
        new FrmDeposito(conta.get(i).getId(),i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void cbxVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVisualizarActionPerformed
        //saldo
        
    }//GEN-LAST:event_cbxVisualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnInfo;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGerarBoleto;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnOutraConta;
    private javax.swing.JButton btnREalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSaque;
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
