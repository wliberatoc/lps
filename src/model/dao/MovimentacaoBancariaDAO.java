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
import model.classes.MovimentacaoBancaria;

/**
 *
 * @author Willian-PC
 */
public class MovimentacaoBancariaDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(MovimentacaoBancaria moviB){
        String sql = "INSERT INTO tbl_movimentacao_bancaria (id_conta, data, valor, id_tipo_operacao, descricao, tipo_movimentacao) VALUES (?, ?, ?, ?, ?, ?)" ;
        try {
            float vl = 0;
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, moviB.getIdConta());
            stmt.setDate(2, new Date(moviB.getData().getTime()));
            if(moviB.getTipoMovimentacao() == 'D'){
                vl = moviB.getValor();
                moviB.setValor((-1*moviB.getValor()));
            }
            stmt.setFloat(3, moviB.getValor());      
            stmt.setInt(4, moviB.getIdTipoOperacao());
            stmt.setString(5, moviB.getDescricao());
            stmt.setString(6, ""+moviB.getTipoMovimentacao());
            stmt.executeUpdate();
            moviB.setValor(vl);
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return false;
        }
    }
    public ArrayList<MovimentacaoBancaria> select(String campo, String query){
        String sql = "SELECT * FROM tbl_movimentacao_bancaria WHERE "+campo+" LIKE ?";
        ArrayList<MovimentacaoBancaria> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while(rs.next()){
                MovimentacaoBancaria moviB = new MovimentacaoBancaria();
                moviB.setId(rs.getInt("id"));                
                moviB.setIdConta(rs.getInt("id_movimentacao_bancaria"));
                moviB.setValor(rs.getFloat("valor"));
                moviB.setData(rs.getDate("data"));
                moviB.setIdTipoOperacao(rs.getInt("id_tipo_opetacao"));
                moviB.setDescricao(rs.getString("descricao"));
                moviB.setTipoMovimentacao(rs.getString("tipo_movimentacao").toCharArray()[0]);            
                lista.add(moviB);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return null;
        }   
    }
    
    public ArrayList<MovimentacaoBancaria> load(int id){
        String sql = "SELECT * FROM tbl_movimentacao_bancaria WHERE id_Conta = ? and tipo_movimentacao != 'S'";
        ArrayList<MovimentacaoBancaria> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                MovimentacaoBancaria moviB = new MovimentacaoBancaria();
                moviB.setId(rs.getInt("id"));                
                moviB.setIdConta(rs.getInt("id_conta"));
                moviB.setValor(rs.getFloat("valor"));
                moviB.setData(rs.getDate("data"));
                moviB.setIdTipoOperacao(rs.getInt("id_tipo_operacao"));
                moviB.setDescricao(rs.getString("descricao"));
                moviB.setTipoMovimentacao(rs.getString("tipo_movimentacao").toCharArray()[0]); 
                lista.add(moviB);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return null;
        }   
    }//fim load
    
    public boolean update(MovimentacaoBancaria moviB){
        String sql = "UPDATE tbl_movimentacao_bancaria SET descricao = ?, id_conta = ?, data = ?, valor = ?, id_tipo_operacao = ?, tipo_movimetacao = ? WHERE tbl_movimentacao_bancaria.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, moviB.getIdConta());
            stmt.setDate(2, new Date(moviB.getData().getTime()));
            stmt.setFloat(3, moviB.getValor());
            stmt.setInt(4, moviB.getIdTipoOperacao());
            stmt.setString(5, moviB.getDescricao());
            stmt.setString(6, ""+moviB.getTipoMovimentacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return false;
        }
    }  
    
    public boolean delete(int id){
        String sql = "DELETE FROM tbl_movimentacao_bancaria WHERE tbl_movimentacao_bancaria.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return false;
        }
    } 
    
}
