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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.classes.Conta;
import model.classes.Data;
import model.dao.ContaDAO;
/**
 *
 * @author Willian-PC
 */
public class FrmSelecionarData extends javax.swing.JFrame {

    /**
     * Creates new form FrmSelecionarData
     * @param id
     * @param i
     * @param view
     */
    ContaDAO contaDao = new ContaDAO();
    ArrayList<Conta> conta  = new ArrayList<>();
    int i;
    String inicio;
    String fim;
    public FrmSelecionarData(int id, int i) {
        initComponents();
        this.i = i;
        conta = contaDao.load(id);
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskData2 = new MaskFormatter("##/##/####");
            
            maskData.install(ftxtInicio);
            maskData2.install(ftxtFim);
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadastroPF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpaCampos(){
        ftxtInicio.setText("");
        ftxtFim.setText("");
    }
    
    public boolean verificaData(){
        if(ftxtInicio.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "É necessário informar a data de início");
            ftxtInicio.requestFocus();
            return false;
        }
        Calendar cal = Calendar.getInstance();
        inicio = ftxtInicio.getText();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int dia = Integer.parseInt(inicio.substring(0,2));
        int mes = Integer.parseInt(inicio.substring(3,5));
        int ano = Integer.parseInt(inicio.substring(6,10));
        if(ano > year)
            return false;
        else if(ano == year)
            if(mes > month || mes < 1)
                return false;
            else if(mes == month && (dia > day || dia < 1))
                return false;
        else if(mes > 12 || mes < 1)
            return false;
        else if((mes == 2 && (ano % 400 == 0)) && dia > 29)
            return false;
        else if((mes == 2 && (ano % 400 != 0)) && dia > 28)
            return false;
        else if(((mes < 8 && mes%2 == 0)||(mes > 7 && mes%2 != 0)) && dia > 30)
            return false;
        else if (dia < 1 || dia > 31)
            return false;
        else if(ftxtFim.getText().isEmpty()){
            fim = ""+dia+"/"+mes+"/"+ano;
            return true;
        }else{
            fim = ftxtFim.getText();
            int diaf = Integer.parseInt(fim.substring(0,2));
            int mesf = Integer.parseInt(fim.substring(3,5));
            int anof = Integer.parseInt(fim.substring(6,10));
            if(ano > anof || anof > year)
                return false;
            else if(ano == anof)
                if(mes > mesf || mesf < 1)
                    return false;
                else if(mes == mesf && (dia > diaf || diaf < 1))
                    return false;
            else if(anof == year)
                if(mesf > month || mes < 1)
                    return false;
                else if(mesf == month && (diaf > day || diaf < 1))
                    return false;            
            else if(mesf > 12 || mesf < 1)
                return false;
            else if((mesf == 2 && (anof % 400 == 0)) && diaf > 29)
                return false;
            else if((mesf == 2 && (anof % 400 != 0)) && diaf > 28)
                return false;
            else if(((mesf < 8 && mesf%2 == 0)||(mesf > 7 && mesf%2 != 0)) && diaf > 30)
                return false;
            return (diaf > 0 || diaf < 32);            
        }  
        return false;
    }//fim verifica inicio
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlData = new javax.swing.JPanel();
        lblDe = new javax.swing.JLabel();
        ftxtInicio = new javax.swing.JFormattedTextField();
        lbA = new javax.swing.JLabel();
        ftxtFim = new javax.swing.JFormattedTextField();
        btnCancelar3 = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnVoltar3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlData.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informe as datas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N

        lblDe.setText("De:");

        ftxtInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtInicioActionPerformed(evt);
            }
        });

        lbA.setText("a");

        ftxtFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFimActionPerformed(evt);
            }
        });

        btnCancelar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\cancela.png")); // NOI18N
        btnCancelar3.setText("Cancelar");
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\confirmar.png")); // NOI18N
        btnSalvar.setText("Confirmar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(btnCancelar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(lblDe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lbA)
                        .addGap(18, 18, 18)
                        .addComponent(ftxtFim, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftxtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ftxtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDe)
                        .addComponent(lbA)))
                .addGap(33, 33, 33)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnVoltar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Willian-PC\\Documents\\NetBeansProjects\\Banco\\src\\imagens\\voltar.png")); // NOI18N
        btnVoltar3.setText(" Voltar");
        btnVoltar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ftxtInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtInicioActionPerformed

    private void ftxtFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFimActionPerformed

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        // Cancelar
        limpaCampos();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Salvar
        if(verificaData()){
            SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
            Data data = new Data();
            try {
                data.setInicio(formataData.parse(inicio));
                data.setFim(formataData.parse(fim));
                new FrmVisualizar(conta.get(0).getId(),i,data).setVisible(true);
                this.dispose();
            } catch (ParseException ex) {
                System.err.println("Erro: "+ex);
            }      
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVoltar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar3ActionPerformed
        // volar
        new FrmHome(conta.get(0).getUsuario(),this.i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltar3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JButton btnVoltar2;
    private javax.swing.JButton btnVoltar3;
    private javax.swing.JFormattedTextField ftxtFim;
    private javax.swing.JFormattedTextField ftxtInicio;
    private javax.swing.JLabel lbA;
    private javax.swing.JLabel lblDe;
    private javax.swing.JPanel pnlData;
    // End of variables declaration//GEN-END:variables
}
