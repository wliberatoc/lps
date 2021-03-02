/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conection.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.classes.ContaPJ;


/**
 *
 * @author Willian-PC
 */
public class ContaPJDAO {
    private Connection con = null;
    public ContaPJDAO(){
        con = Conexao.getConnection();
    }
    
    public boolean insert(ContaPJ conta){
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tbl_contapj (numero_da_conta, agencia, tipo, saldo, qtd_teds, qtd_saques, limite_teds, limite_saques, data_abertura, cnpj_titular) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, conta.getNumeroDaConta());
            stmt.setString(2, conta.getAgencia());
            stmt.setString(3, ""+conta.getTipo());
            stmt.setFloat(4, conta.getSaldo());
            stmt.setInt(5, conta.getTeds());
            stmt.setInt(6, conta.getSaques());
            stmt.setFloat(7, conta.getLimiteTeds());
            stmt.setFloat(8, conta.getLimiteSaques());
            stmt.setDate(9,new Date(conta.getAbertura().getTime()));
            stmt.setString(10, conta.getCnpjTitular());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    public ArrayList<ContaPJ> select(String campo, String query){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_contapj WHERE "+campo+" LIKE ?";
        ArrayList<ContaPJ> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                ContaPJ conta = new ContaPJ();
                conta.setId(rs.getInt("id_pf"));
                conta.setNumeroDaConta(rs.getString("numero_da_conta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setTipo(rs.getString("tipo").toCharArray()[0]);
                conta.setSaldo(rs.getFloat("saldo"));
                conta.setTeds(rs.getInt("qtd_teds"));
                conta.setSaques(rs.getInt("qtd_saques"));
                conta.setLimiteTeds(rs.getInt("limite_teds"));
                conta.setLimiteSaques(rs.getInt("limite_saques"));
                conta.setAbertura(rs.getDate("data_abertura"));
                conta.setCnpjTitular(rs.getString("cnpj_titular"));
                lista.add(conta);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return null;
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }    
    }
    
    public ArrayList<ContaPJ> selectAll(){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_contapj ";
        ArrayList<ContaPJ> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                ContaPJ conta = new ContaPJ();
                conta.setId(rs.getInt("id_pf"));
                conta.setNumeroDaConta(rs.getString("numero_da_conta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setTipo(rs.getString("tipo").toCharArray()[0]);
                conta.setSaldo(rs.getFloat("saldo"));
                conta.setTeds(rs.getInt("qtd_teds"));
                conta.setSaques(rs.getInt("qtd_saques"));
                conta.setLimiteTeds(rs.getFloat("limite_teds"));
                conta.setLimiteTeds(rs.getFloat("limite_saques"));
                conta.setAbertura(rs.getDate("data_abertura"));
                conta.setCnpjTitular(rs.getString("cnpj_titular"));
                lista.add(conta);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return null;
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }    
    }
    
    public boolean update(ContaPJ conta){
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_contapj SET numero_da_conta = ?, data_abertura = ?, tipo = ?, tipo = ?, saldo = ?, qtd_teds = ?, qtd_saques = ?, limite_teds = ?, cnpj_titular = ? WHERE tbl_contapj.id_pf = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, conta.getNumeroDaConta());
            stmt.setString(2, conta.getAgencia());
            stmt.setString(3, ""+conta.getTipo());
            stmt.setFloat(4, conta.getSaldo());
            stmt.setInt(5, conta.getTeds());
            stmt.setInt(6, conta.getSaques());
            stmt.setFloat(7, conta.getLimiteTeds());
            stmt.setFloat(8, conta.getLimiteSaques());
            stmt.setDate(9,new Date(conta.getAbertura().getTime()));
            stmt.setString(10, conta.getCnpjTitular());
            stmt.setInt(11, conta.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }  
    
    public boolean delete(ContaPJ conta){
        PreparedStatement stmt = null;
        String sql = "DELETE FROM tbl_contapj WHERE tbl_contapj.id_pf = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, conta.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }  
}

