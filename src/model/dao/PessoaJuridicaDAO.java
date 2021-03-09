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
import model.classes.PessoaJuridica;


/**
 *
 * @author Willian-PC
 */
public class PessoaJuridicaDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(PessoaJuridica pj){
        String sql = "INSERT INTO tbl_pessoa_juridica (cnpj, nome, fundacao, email, telefone, endereco, senha, senha_login) VALUES (?,?,?,?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, pj.getCnpj());
            stmt.setString(2, pj.getNome());
            stmt.setDate(3,new Date(pj.getFundacao().getTime()));
            stmt.setString(4, pj.getEmail());
            stmt.setString(5, pj.getTelefone());
            stmt.setString(6, pj.getEndereco());
            stmt.setInt(7, pj.getSenha());
            stmt.setString(8, pj.getSenhaLogin());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Jurídica: "+ex);
            return false;
        }
    }
    public ArrayList<PessoaJuridica> select(String campo, String query){     
        String sql = "SELECT * FROM tbl_pessoa_juridica WHERE "+campo+" LIKE ?";
        ArrayList<PessoaJuridica> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                PessoaJuridica cliente = new PessoaJuridica();
                cliente.setId(rs.getInt("id"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setNome(rs.getString("nome"));
                cliente.setFundacao(rs.getDate("fundacao"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSenha(rs.getInt("senha"));
                cliente.setSenhaLogin(rs.getString("senha_login"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Jurídica: "+ex);
            return null;
        }   
    }
    
    public boolean select2Campos(String campo, String query, String campo2, int query2){     
        String sql = "SELECT * FROM tbl_pessoa_juridica WHERE "+campo+" LIKE ? AND "+campo2+" = ?";
        ArrayList<PessoaJuridica> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            stmt.setInt(2, query2);
            rs = stmt.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Jurídica: "+ex);
            return false;
        }    
    }//fim select2Campos
   
    public boolean update(PessoaJuridica cliente){
        String sql = "UPDATE tbl_pessoa_juridica SET cnpj = ?, nome = ?, fundacao = ?, email = ?, telefone = ?, endereco = ?, senha = ?, senha_login = ? WHERE tbl_pessoa_juridica.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, cliente.getCnpj());
            stmt.setString(2, cliente.getNome());
            stmt.setDate(3,new Date(cliente.getFundacao().getTime()));
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco());
            stmt.setInt(7, cliente.getSenha());
            stmt.setString(8, cliente.getSenhaLogin());
            stmt.setInt(9, cliente.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Jurídica: "+ex);
            return false;
        }
    }  
    
    public boolean delete(int id){
        String sql = "DELETE FROM tbl_pessoa_juridica WHERE tbl_pessoa_juridica.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Jurídica: "+ex);
            return false;
        }
    }  
}
