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
import model.classes.Mes;

/**
 *
 * @author Willian-PC
 */
public class MesDAO {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean insert(Mes mes){
        String sql = "INSERT INTO tbl_mes (data) VALUES (?)" ;
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setDate(1, new Date(mes.getData().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Mes: "+ex);
            return false;
        }
    }

    
    public String load(int id){
        String sql = "SELECT * FROM tbl_mes WHERE tbl_mes.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Mes mes = new Mes();
            rs.next();
            mes.setData(rs.getDate("data"));
            return mes.getData().toString();
        } catch (SQLException ex) {
            System.err.println("Erro Mes: "+ex);
            return null;
        }   
    }//fim load
    
    public boolean update(Mes mes){
        String sql = "UPDATE tbl_mes SET data = ? WHERE tbl_mes.id = ?";
        try {
            stmt = Persistencia.getConnection().prepareStatement(sql);
            stmt.setDate(1, new Date(mes.getData().getTime()));
            stmt.setInt(2, mes.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro Mes: "+ex);
            return false;
        }
    }
    
}
