/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.classes.Conta;
import model.classes.MovimentacaoBancaria;
import model.dao.ContaDAO;
import model.dao.MovimentacaoBancariaDAO;

/**
 *
 * @author Willian-PC
 */
public class FrmVisualizar extends javax.swing.JFrame {

    /** Creates new form FrmVizualizar */
    ContaDAO contaDao = new ContaDAO();
    ArrayList<Conta> conta  = new ArrayList<>();
    int i;
    String d1,d2;
    public FrmVisualizar(int id, int i,String d1, String d2) {
        initComponents();
        this.i = i;
        conta = contaDao.load(id);
        carregarTabela();
        this.d1 = d1;
        this.d2 = d2;
    }
    public final void carregarTabela(){
        String [] colunas = {"Data","Tipo", "Descricao", "Valor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        ArrayList<MovimentacaoBancaria> moviB = mvb.extrato(d1, d2, conta.get(0).getId());
        for(int j=0; j<moviB.size(); j++){
                Object [] linha = {moviB.get(j).getData(), mvb.pegaTipoOperacao(moviB.get(j).getIdTipoOperacao()), moviB.get(j).getDescricao(),moviB.get(j).getValor()};
                modelo.addRow(linha);
            }
        tblVisualizar.setModel(modelo);        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVisualizar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVisualizar = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlVisualizar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Visualizar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N

        tblVisualizar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblVisualizar);

        javax.swing.GroupLayout pnlVisualizarLayout = new javax.swing.GroupLayout(pnlVisualizar);
        pnlVisualizar.setLayout(pnlVisualizarLayout);
        pnlVisualizarLayout.setHorizontalGroup(
            pnlVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVisualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVisualizarLayout.setVerticalGroup(
            pnlVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVisualizarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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
                    .addComponent(pnlVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // volar
        new FrmHome(conta.get(0).getUsuario(),this.i).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlVisualizar;
    private javax.swing.JTable tblVisualizar;
    // End of variables declaration//GEN-END:variables

}