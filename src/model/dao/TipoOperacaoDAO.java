/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.classes.TipoOperacao;

/**
 *
 * @author Willian-PC
 */
public class TipoOperacaoDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(TipoOperacao tipoOperacao){
        String sql = "INSERT INTO tbl_tipo_operacao (descricao) VALUES (?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, tipoOperacao.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return false;
        }
    }
    public ArrayList<TipoOperacao> select(String campo, String query){
        String sql = "SELECT * FROM tbl_tipo_operacao WHERE "+campo+" LIKE ?";
        ArrayList<TipoOperacao> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoOperacao tipoOperacao = new TipoOperacao();
                tipoOperacao.setId(rs.getInt("id"));                
                tipoOperacao.setDescricao(rs.getString("descricao"));
                lista.add(tipoOperacao);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return null;
        }   
    }
    
    public ArrayList<TipoOperacao> selectAll(){
        String sql = "SELECT * FROM tbl_tipo_operacao";
        ArrayList<TipoOperacao> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoOperacao tipoOperacao = new TipoOperacao();
                tipoOperacao.setId(rs.getInt("id"));                
                tipoOperacao.setDescricao(rs.getString("descricao"));
                lista.add(tipoOperacao);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return null;
        }   
    }
    
    public ArrayList<TipoOperacao> load(int id){
        String sql = "SELECT * FROM tbl_tipo_operacao WHERE tbl_tipo_operacao.id = ?";
        ArrayList<TipoOperacao> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoOperacao tipoOperacao = new TipoOperacao();
                tipoOperacao.setId(rs.getInt("id"));                
                tipoOperacao.setDescricao(rs.getString("descricao"));
                lista.add(tipoOperacao);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return null;
        }   
    }//fim load
    
    public boolean update(TipoOperacao tipoOperacao){
        String sql = "UPDATE tbl_tipo_operacao SET descricao = ? WHERE tbl_tipo_operacao.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, tipoOperacao.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return false;
        }
    } 
    public boolean delete(int id){
        String sql = "DELETE FROM tbl_tipo_operacao WHERE tbl_tipo_operacao.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return false;
        }
    } 
}
