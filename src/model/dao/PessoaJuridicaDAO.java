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
import model.classes.PessoaJuridica;


/**
 *
 * @author Willian-PC
 */
public class PessoaJuridicaDAO {
    private Connection con = null;
    
    public PessoaJuridicaDAO(){
        con = Conexao.getConnection();
    }
    
    public boolean insert(PessoaJuridica pj){
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tbl_pessoa_juridica (cnpj, nome, fundacao, email, telefone, endereco, senha, senha_login) VALUES (?,?,?,?,?,?,?,?)" ;
        try {
            stmt = con.prepareStatement(sql);
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
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    public ArrayList<PessoaJuridica> select(String campo, String query){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_pessoa_juridica WHERE "+campo+" LIKE ?";
        ArrayList<PessoaJuridica> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                PessoaJuridica cliente = new PessoaJuridica();
                cliente.setId(rs.getInt("id_pj"));
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
            System.err.println("Erro: "+ex);
            return null;
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }    
    }
    
    public ArrayList<PessoaJuridica> selectAll(){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_pessoa_juridica ";
        ArrayList<PessoaJuridica> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                PessoaJuridica cliente = new PessoaJuridica();
                cliente.setId(rs.getInt("id_pj"));
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
            System.err.println("Erro: "+ex);
            return null;
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }    
    }
    
    public boolean update(PessoaJuridica cliente){
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_pessoa_juridica SET cnpj = ?, nome = ?, fundacao = ?, email = ?, telefone = ?, endereco = ?, senha = ?, senha_login = ? WHERE tbl_pessoa_juridica.id_pj = ?";
        try {
            stmt = con.prepareStatement(sql);
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
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }  
    
    public boolean delete(PessoaJuridica cliente){
        PreparedStatement stmt = null;
        String sql = "DELETE FROM tbl_pessoa_juridica WHERE tbl_pessoa_juridica.id_pj = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
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
