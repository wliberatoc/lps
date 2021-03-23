/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.classes.Data;
import model.classes.MovimentacaoBancaria;
import model.classes.TipoOperacao;
import model.dao.MovimentacaoBancariaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerMovimentacaoBancaria {
    
    public static boolean insert(int id, char tipoM, Date data, int tipoOp, String descricao, float valor){
        MovimentacaoBancaria mvb = new MovimentacaoBancaria();
        mvb.setIdTipoOperacao(id);
        mvb.setTipoMovimentacao(tipoM);
        mvb.setData(data);
        mvb.setIdTipoOperacao(tipoOp);
        mvb.setDescricao(descricao);
        mvb.setValor(valor);
        MovimentacaoBancariaDAO mvbD = new MovimentacaoBancariaDAO();
        return mvbD.insert(mvb);
    }
    
    public static ArrayList<Object []> pegaTipos(){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        ArrayList<TipoOperacao> lista = mvb.pegaTiposOperacao();
         ArrayList<Object []> tipos = new ArrayList<>();
        for(int i=0; i<lista.size(); i++){
            Object [] tipo  = new Object[2];
            tipo[0] = ((TipoOperacao) lista.get(i)).getId();
            tipo[1] = ((TipoOperacao) lista.get(i)).getDescricao();
            tipos.add(tipo);
        }
        return tipos;
    }
    
    public static int pegaIdTipo(String descricao){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.pegaIdOperacao(descricao);
    }
    
    public static String pegaTipo(int id){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.pegaTipoOperacao(id);
    }
    
    public static ArrayList<Object []> extrato(int id, String inicio, String fim){
        MovimentacaoBancariaDAO mvbDao = new MovimentacaoBancariaDAO();
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
        Data data = new Data();
        try {
            data.setInicio(formataData.parse(inicio));
            data.setFim(formataData.parse(fim));
            ArrayList<MovimentacaoBancaria> lista = mvbDao.extrato(id, data);
            ArrayList<Object []> mvbs = new ArrayList<>();
            for(int i=0; i<lista.size(); i++){
                Object [] mvb  = new Object[4];
                mvb[0] = ((MovimentacaoBancaria) lista.get(i)).getValor();
                mvb[1] = ((MovimentacaoBancaria) lista.get(i)).getData();
                mvb[2] = ((MovimentacaoBancaria) lista.get(i)).getIdTipoOperacao();
                mvb[3] = ((MovimentacaoBancaria) lista.get(i)).getDescricao();
                mvbs.add(mvb);
            }
            return mvbs;
        } catch (ParseException ex) {
            System.err.println("Erro: "+ex);
            return null;
        }
        
        
        
    }
}
