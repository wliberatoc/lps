/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import model.classes.Boleto;

/**
 *
 * @author Willian-PC
 */
public class BoletoDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(Boleto boleto){
        String sql = "INSERT INTO tbl_boleto (codigo_de_barras, id_conta, valor, data_de_vencimento, pago) VALUES (?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, boleto.getCodigoDeBarras());
            stmt.setInt(2, boleto.getIdConta());
            stmt.setFloat(3, boleto.getValor());
            stmt.setDate(4, new Date(boleto.getDataDeVencimento().getTime()));
            stmt.setBoolean(5, boleto.isPago());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Boleto: "+ex);
            return false;
        }
    }
    
    public Boleto select(String query){
        String sql = "SELECT * FROM tbl_boleto WHERE codigo_de_barras LIKE ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            Boleto boleto = new Boleto();
            while(rs.next()){
                boleto.setId(rs.getInt("id"));                
                boleto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
                boleto.setIdConta(rs.getInt("id_conta"));
                boleto.setValor(rs.getFloat("valor"));
                boleto.setDataDeVencimento(rs.getDate("data_de_vencimento"));
                boleto.setPago(rs.getBoolean("pago"));
            }
            return boleto;
        } catch (SQLException ex) {
            System.err.println("Erro Boleto: "+ex);
            return null;
        }   
    }

    public boolean update(Boleto boleto){
        String sql = "UPDATE tbl_boleto SET codigo_de_barras = ?, id_conta = ?, valor = ?, data_de_vencimento = ?, pago = ? WHERE tbl_boleto.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, boleto.getCodigoDeBarras());
            stmt.setInt(2, boleto.getIdConta());
            stmt.setFloat(3, boleto.getValor());
            stmt.setDate(4, new Date(boleto.getDataDeVencimento().getTime()));
            stmt.setBoolean(5, boleto.isPago());
            stmt.setInt(6, boleto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Boleto: "+ex);
            return false;
        }
    }  
}
