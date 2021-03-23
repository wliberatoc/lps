/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Persistencia;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.classes.Conta;


/**
 *
 * @author Willian-PC
 */
public class ContaDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(Conta conta){
        String sql = "INSERT INTO tbl_conta (numero_da_conta, agencia, tipo, saldo, qtd_transacoes, qtd_saques, limite_teds, limite_saques, data_abertura, usuario) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, conta.getNumeroDaConta());
            stmt.setString(2, conta.getAgencia());
            stmt.setInt(3, conta.getTipo());
            stmt.setFloat(4, conta.getSaldo());
            stmt.setInt(5, conta.getQtdTransacoes());
            stmt.setInt(6, conta.getQtdSaques());
            stmt.setFloat(7, conta.getLimiteTeds());
            stmt.setFloat(8, conta.getLimiteSaques());
            stmt.setDate(9,new Date(conta.getAbertura().getTime()));
            stmt.setString(10, conta.getUsuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return false;
        }
    }
    
    public ArrayList<Conta> select(String campo, String query){
        String sql = "SELECT * FROM tbl_conta WHERE "+campo+" LIKE ?";
        ArrayList<Conta> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                Conta conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNumeroDaConta(rs.getString("numero_da_conta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setTipo(rs.getInt("tipo"));
                conta.setSaldo(rs.getFloat("saldo"));
                conta.setQtdTransacoes(rs.getInt("qtd_transacoes"));
                conta.setQtdSaques(rs.getInt("qtd_saques"));
                conta.setLimiteTeds(rs.getInt("limite_teds"));
                conta.setLimiteSaques(rs.getInt("limite_saques"));
                conta.setAbertura(rs.getDate("data_abertura"));
                conta.setUsuario(rs.getString("usuario"));
                lista.add(conta);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return null;
        }   
    }
    
    public Conta load(int id){
        String sql = "SELECT * FROM tbl_conta WHERE tbl_conta.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Conta conta = new Conta();
            while(rs.next()){
                conta.setId(rs.getInt("id"));
                conta.setNumeroDaConta(rs.getString("numero_da_conta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setTipo(rs.getInt("tipo"));
                conta.setSaldo(rs.getFloat("saldo"));
                conta.setQtdTransacoes(rs.getInt("qtd_transacoes"));
                conta.setQtdSaques(rs.getInt("qtd_saques"));
                conta.setLimiteTeds(rs.getInt("limite_teds"));
                conta.setLimiteSaques(rs.getInt("limite_saques"));
                conta.setAbertura(rs.getDate("data_abertura"));
                conta.setUsuario(rs.getString("usuario"));
            }
            return conta;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return null;
        }   
    }//fim load
    
    public String pegaNomeUsePf(String use){
        String sql = "SELECT nome FROM tbl_pessoa_fisica WHERE cpf LIKE ? ";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, use);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("nome");
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return null;
        }   
    }
    
    public String pegaNomeUsePJ(String use){
        String sql = "SELECT nome FROM tbl_pessoa_juridica WHERE cnpj LIKE ? ";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, use);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("nome");
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return null;
        }   
    }
    
    public boolean update(Conta conta){
        String sql = "UPDATE tbl_conta SET  saldo = ?, qtd_transacoes = ?, qtd_saques = ? WHERE tbl_conta.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setFloat(1, conta.getSaldo());
            stmt.setInt(2, conta.getQtdTransacoes());
            stmt.setInt(3, conta.getQtdSaques());
            stmt.setInt(4, conta.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return false;
        }
    }  
    
    public boolean delete(int id){
        String sql = "DELETE FROM tbl_conta WHERE tbl_conta.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return false;
        }
    }  
    
    public boolean deleteAll(String use){
        String sql = "DELETE FROM tbl_conta WHERE tbl_conta.usuario = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, use);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Conta: "+ex);
            return false;
        }
    }  
}
