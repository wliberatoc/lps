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
import model.classes.PessoaFisica;


/**
 *
 * @author Willian-PC
 */
public class PessoaFisicaDAO {
    private Connection con = null;
    public PessoaFisicaDAO(){
        con = Conexao.getConnection();
    }
    
    public boolean insert(PessoaFisica cliente){
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tbl_pessoa_fisica (cpf, nome, data_de_nascimento, sexo, email, telefone, endereco, senha, senha_login) VALUES (?,?,?,?,?,?,?,?,?)" ;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setDate(3,new Date(cliente.getNascimento().getTime()));
            stmt.setString(4, ""+cliente.getSexo());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getEndereco());
            stmt.setInt(8, cliente.getSenha());
            stmt.setString(9, cliente.getSenhaLogin());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }  

    public ArrayList<PessoaFisica> select(String campo, String query){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_pessoa_fisica WHERE "+campo+" LIKE ?";
        ArrayList<PessoaFisica> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                PessoaFisica cliente = new PessoaFisica();
                cliente.setId(rs.getInt("id_pf"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getDate("data_de_nascimento"));
                cliente.setSexo(rs.getString("sexo").toCharArray()[0]);
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
    
    public ArrayList<PessoaFisica> selectAll(){     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_pessoa_fisica ";
        ArrayList<PessoaFisica> lista  = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                PessoaFisica cliente = new PessoaFisica();
                cliente.setId(rs.getInt("id_pf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getDate("data_de_nascimento"));
                cliente.setSexo(rs.getString("sexo").toCharArray()[0]);
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
    
    
    public boolean update(PessoaFisica cliente){
        PreparedStatement stmt = null;
        String sql = "UPDATE tbl_pessoa_fisica SET cpf = ?, nome = ?, data_de_nascimento = ?, sexo = ?, email = ?, telefone = ?, endereco = ?, senha = ?, senha_login = ? WHERE tbl_pessoa_fisica.id_pf = ?";
        try {
            System.out.println(cliente.getId());
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setDate(3,new Date(cliente.getNascimento().getTime()));
            stmt.setString(4, ""+cliente.getSexo());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getEndereco());
            stmt.setInt(8, cliente.getSenha());
            stmt.setString(9, cliente.getSenhaLogin());
            stmt.setInt(10, cliente.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }  
    
    public boolean delete(PessoaFisica cliente){
        PreparedStatement stmt = null;
        String sql = "DELETE FROM tbl_pessoa_fisica WHERE tbl_pessoa_fisica.id_pf = ?";
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
    