/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conection.Persistencia;
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
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(ContaPJ conta){
        String sql = "INSERT INTO tbl_contapj (numero_da_conta, agencia, tipo, saldo, qtd_teds, qtd_saques, limite_teds, limite_saques, data_abertura, cnpj_titular) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
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
        }
    }
    public ArrayList<ContaPJ> select(String campo, String query){     
        String sql = "SELECT * FROM tbl_contapj WHERE "+campo+" LIKE ?";
        ArrayList<ContaPJ> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                ContaPJ conta = new ContaPJ();
                conta.setId(rs.getInt("id_pj"));
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
        }   
    }
    
    public ArrayList<ContaPJ> load(int id){     
        String sql = "SELECT * FROM tbl_contapj WHERE tbl_contapj.id_pj = ?";
        ArrayList<ContaPJ> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                ContaPJ conta = new ContaPJ();
                conta.setId(rs.getInt("id_pj"));
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
        }   
    }
    
    public ArrayList<ContaPJ> selectAll(){     
        String sql = "SELECT * FROM tbl_contapj ";
        ArrayList<ContaPJ> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
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
        }   
    }
    
    public boolean update(ContaPJ conta){
        String sql = "UPDATE tbl_contapj SET numero_da_conta = ?, data_abertura = ?, agencia = ?, tipo = ?, saldo = ?, qtd_teds = ?, qtd_saques = ?, limite_teds = ?, cnpj_titular = ? WHERE tbl_contapj.id_pj = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
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
        }
    }  
    
    public boolean delete(int id){
        String sql = "DELETE FROM tbl_contapj WHERE tbl_contapj.id_pf = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }
    }  
    public boolean deleteAll(String cnpj){
        String sql = "DELETE FROM tbl_contapj WHERE tbl_contapj.cnpj_titular = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, cnpj);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }
    }  
}

