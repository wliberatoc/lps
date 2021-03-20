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
import model.classes.PessoaFisica;


/**
 *
 * @author Willian-PC
 */
public class PessoaFisicaDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(PessoaFisica cliente){
        String sql = "INSERT INTO tbl_pessoa_fisica (cpf, nome, data_de_nascimento, sexo, email, telefone, endereco, senha, senha_login) VALUES (?,?,?,?,?,?,?,?,?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
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
            System.err.println("Erro Pessoa Física: "+ex);
            return false;
        }
    }  

    public PessoaFisica select(String query){     
        String sql = "SELECT * FROM tbl_pessoa_fisica WHERE cpf LIKE ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            PessoaFisica cliente = new PessoaFisica();
            while(rs.next()){
                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getDate("data_de_nascimento"));
                cliente.setSexo(rs.getString("sexo").toCharArray()[0]);
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSenha(rs.getInt("senha"));
                cliente.setSenhaLogin(rs.getString("senha_login"));
            }
            return cliente;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Física: "+ex);
            return null;
        }    
    }//fim select
    
    public boolean update(PessoaFisica cliente){
        String sql = "UPDATE tbl_pessoa_fisica SET cpf = ?, nome = ?, data_de_nascimento = ?, sexo = ?, email = ?, telefone = ?, endereco = ?, senha = ?, senha_login = ? WHERE tbl_pessoa_fisica.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
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
            System.err.println("Erro Pessoa Física: "+ex);
            return false;
        }
    }//fim update  
    
    public boolean delete(String use){
        String sql = "DELETE FROM tbl_pessoa_fisica WHERE cpf LIKE ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, use);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Pessoa Física: "+ex);
            return false;
        }
    }//fim delete  
    
}//fim class DAO
    
