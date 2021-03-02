/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conection.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import model.classes.Boleto;

/**
 *
 * @author Willian-PC
 */
public class BoletoDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(Boleto boleto){
        String sql = "INSERT INTO tbl_boleto (codigo_de_barras, numero_da_boleto, agencia, tipo, saldo, qtd_teds, qtd_saques, limite_teds, limite_saques, data_abertura, cpf_titular) VALUES (?,?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, boleto.getCodigoDeBarras());
            stmt.setString(2, boleto.getNumeroDaConta());
            stmt.setString(3, boleto.getAgencia());
            stmt.setString(4, ""+boleto.getTipo());
            stmt.setFloat(5, boleto.getValor());
            stmt.setDate(6, new Date(boleto.getDataDeVencimento().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }
    }
    public ArrayList<Boleto> select(String campo, String query){
        String sql = "SELECT * FROM tbl_boleto WHERE "+campo+" LIKE ?";
        ArrayList<Boleto> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                Boleto boleto = new Boleto();
                boleto.setId(rs.getInt("id_boleto"));                
                boleto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
                boleto.setNumeroDaConta(rs.getString("numero_da_conta"));
                boleto.setAgencia(rs.getString("agencia"));
                boleto.setTipo(rs.getString("tipo").toCharArray()[0]);
                boleto.setValor(rs.getFloat("valor"));
                boleto.setDataDeVencimento(rs.getDate("data_de_vencimento"));
                lista.add(boleto);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return null;
        }   
    }
    
    public ArrayList<Boleto> load(int id){
        String sql = "SELECT * FROM tbl_boleto WHERE tbl_boleto.id_boleto = ?";
        ArrayList<Boleto> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                Boleto boleto = new Boleto();
                boleto.setId(rs.getInt("id_boleto"));                
                boleto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
                boleto.setNumeroDaConta(rs.getString("numero_da_conta"));
                boleto.setAgencia(rs.getString("agencia"));
                boleto.setTipo(rs.getString("tipo").toCharArray()[0]);
                boleto.setValor(rs.getFloat("valor"));
                boleto.setDataDeVencimento(rs.getDate("data_de_vencimento"));
                lista.add(boleto);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return null;
        }   
    }//fim load
    
    public boolean update(Boleto boleto){
        String sql = "UPDATE tbl_boleto SET codigo_de_barras = ?, numero_da_conta = ?, agencia = ?, tipo = ?, valor = ?, data_de_vencimento = ? WHERE tbl_boleto.id_boleto = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, boleto.getCodigoDeBarras());
            stmt.setString(2, boleto.getNumeroDaConta());
            stmt.setString(3, boleto.getAgencia());
            stmt.setString(4, ""+boleto.getTipo());
            stmt.setFloat(5, boleto.getValor());
            stmt.setDate(6, new Date(boleto.getDataDeVencimento().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }
    }  
}
