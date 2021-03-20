/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        mvb.setIdConta(id);
        mvb.setTipoMovimentacao(tipoM);
        mvb.setData(data);
        mvb.setIdTipoOperacao(tipoOp);
        mvb.setDescricao(descricao);
        mvb.setValor(valor);
        MovimentacaoBancariaDAO mvbD = new MovimentacaoBancariaDAO();
        return mvbD.insert(mvb);
    }
    
    public static ArrayList<TipoOperacao> pegaTipos(){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.pegaTiposOperacao();
    }
    
    public static int pegaIdTipo(String descricao){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.pegaIdOperacao(descricao);
    }
    
    public static String pegaTipo(int id){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.pegaTipoOperacao(id);
    }
    
    public static ArrayList<MovimentacaoBancaria> extrato(int id, Data data){
        MovimentacaoBancariaDAO mvb = new MovimentacaoBancariaDAO();
        return mvb.extrato(id, data);
    }
    
    
}
