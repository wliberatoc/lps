/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import model.classes.Data;
import model.classes.MovimentacaoBancaria;
import model.classes.TipoOperacao;

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
            float vl = moviB.getValor();
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, moviB.getIdConta());
            stmt.setDate(2, new Date(moviB.getData().getTime()));
            if(moviB.getTipoMovimentacao() == 'D')                
                moviB.setValor((-1*moviB.getValor()));
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
    
    public ArrayList<MovimentacaoBancaria> load(int id){
        String sql = "SELECT * FROM tbl_movimentacao_bancaria WHERE id_conta = ? and tipo_movimentacao != 'S'";
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
    
    public ArrayList<TipoOperacao> pegaTiposOperacao(){
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
    
    public int pegaIdOperacao(String descricao){
        String sql = "SELECT id FROM tbl_tipo_operacao WHERE descricao LIKE ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return 0;
        }   
    }
    
    public String pegaTipoOperacao(int id){
        String sql = "SELECT descricao FROM tbl_tipo_operacao WHERE tbl_tipo_operacao.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("descricao");
        } catch (SQLException ex) {
            System.err.println("Erro Tipo Operação: "+ex);
            return null;
        }   
    }
    
    public ArrayList<MovimentacaoBancaria> extrato(int id, Data data){
        String sql = "SELECT data,id_tipo_operacao,descricao,valor FROM tbl_movimentacao_bancaria WHERE data BETWEEN ? AND ? and id_conta = ? ORDER BY data";
        ArrayList<MovimentacaoBancaria> lista  = new ArrayList<>();
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setDate(1, new Date(data.getInicio().getTime()));
            stmt.setDate(2, new Date(data.getFim().getTime()));
            stmt.setInt(3, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                MovimentacaoBancaria moviB = new MovimentacaoBancaria();
                moviB.setValor(rs.getFloat("valor"));
                moviB.setData(rs.getDate("data"));
                moviB.setIdTipoOperacao(rs.getInt("id_tipo_operacao"));
                moviB.setDescricao(rs.getString("descricao"));
                lista.add(moviB);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return null;
        }
           
    }//fim load
    
    public float[] saldo(int id, Data data){
        String sql = "SELECT valor FROM tbl_movimentacao_bancaria WHERE id_conta = ? and tipo_movimentacao == 'S' and data == ?";
        float [] lista  = null;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setDate(2, new Date(data.getInicio().getTime()));
            rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()){
                MovimentacaoBancaria moviB = new MovimentacaoBancaria();
                moviB.setValor(rs.getFloat("valor"));
                lista[i] = moviB.getValor();
                i++;
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro Movimentação bancária: "+ex);
            return null;
        }
           
    }//fim load

}
