/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.classes.PessoaJuridica;
import model.dao.PessoaJuridicaDAO;

/**
 *
 * @author Willian-PC
 */
public class FrmEditPJ extends javax.swing.JFrame {

    /**
     * Creates new form frmCadastrar
     */
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    PessoaJuridica cliente = new PessoaJuridica();
    private int volta = 0;        
    public FrmEditPJ(PessoaJuridica clienteParaEditar) {
        initComponents();
        cliente = clienteParaEditar;
        volta = clienteParaEditar.getId();
        preencheCampos();
        
    }
    
    public String editaData(){
        String data = cliente.getFundacao().toString();
        data = data.replace("-", "/");
        data = data.substring(8,10) + data.substring(4,7) +"/"+ data.substring(0,4);
        return data;
    }
    
    public void preencheCampos(){
        ftxtCnpj.setText(cliente.getCnpj());
        txtNome.setText(cliente.getNome());
        ftxtFundacao.setText(editaData());
        txtEmail.setText(cliente.getEmail());
        ftxtTelefone.setText(cliente.getTelefone());
        txtEndereco.setText(cliente.getEndereco());       
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
    
    public boolean validaCampos(){
      if(!txtNome.getText().replace(" ", "").matches("[A-Za-z]{3,}")){
          JOptionPane.showMessageDialog(rootPane, "Preencha o nome corretamente");
          txtNome.requestFocus();
          return false;
      }
      if(ftxtCnpj.getText().replace(" ", "").length() < 14){
        JOptionPane.showMessageDialog(rootPane, "Preencha o CPF corretamente");
        ftxtCnpj.requestFocus();
        return false;
      }
      else{
        if(!verificarCNPJ(ftxtCnpj.getText())){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CPF corretamente");
            ftxtCnpj.requestFocus();
            return false;
        }  
      }
      if(!txtEmail.getText().matches("[A-Za-z0-9]+[@][A-Za-z0-9]+[.][A-Za-z0-9]+[.]*[A-Za-z0-9]*")){
        JOptionPane.showMessageDialog(rootPane, "Preencha o Email corretamente, deve conter o @");
        txtEmail.requestFocus();
        return false;
      }  
      if(ftxtFundacao.getText().replace(" ", "").length() < 10){
        JOptionPane.showMessageDialog(rootPane, "Preencha a data de nascimento corretamente");
        ftxtFundacao.requestFocus();
        return false;
      }  
      if(!verificaData(ftxtFundacao.getText())){
        JOptionPane.showMessageDialog(rootPane, "Preencha a data de nascimento corretamente");
        ftxtFundacao.requestFocus();
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
      return true;
    }
    
    public boolean salvar(){
        try {
            cliente.setCnpj(ftxtCnpj.getText());
            cliente.setNome(txtNome.getText());
            cliente.setFundacao(formataData.parse(ftxtFundacao.getText()));
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(ftxtTelefone.getText());
            cliente.setEndereco(txtEndereco.getText());
            PessoaJuridicaDAO editar = new PessoaJuridicaDAO();
            return editar.update(cliente);  
        } catch (ParseException ex) {
            Logger.getLogger(FrmEditPJ.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlCadastro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCnpj = new javax.swing.JLabel();
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
        lblTitulo = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");

        pnlCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCadastro.setToolTipText("pnlCadastroPf");

        lblNome.setText("Nome:");

        lblCnpj.setText("CNPJ:");

        ftxtCnpj.setEditable(false);

        lblFundacao.setText("Data de fundação:");

        lblTelefone.setText("Telefone:");

        lblEmail.setText("E-mail:");

        lblEndereco.setText("Endereço:");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        lblTitulo.setText("Editar");
        lblTitulo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(lblNome)
                            .addComponent(lblFundacao)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblTelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addComponent(lblEndereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco)))
                        .addContainerGap())))
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(ftxtFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(lblTitulo))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblCnpj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblCnpj)
                        .addGap(23, 23, 23))
                    .addComponent(ftxtCnpj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNome)
                .addGap(3, 3, 3)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFundacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftxtFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(32, Short.MAX_VALUE))
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
                .addComponent(pnlCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        preencheCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        if(validaCampos()){
            if(salvar()){ 
                JOptionPane.showMessageDialog(rootPane,"Edição realizada com sucesso");
                    new FrmHomePJ(cliente.getId()).setVisible(true);
                    this.dispose();
            }
            else
                JOptionPane.showMessageDialog(rootPane,"Erro ao editar usuário");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmHomePJ(cliente.getId()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JFormattedTextField ftxtCnpj;
    private javax.swing.JFormattedTextField ftxtFundacao;
    private javax.swing.JFormattedTextField ftxtTelefone;
    private javax.swing.JLabel lblCnpj;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblFundacao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
