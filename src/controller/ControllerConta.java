/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import model.classes.Conta;
import model.classes.MovimentacaoBancaria;
import model.dao.ContaDAO;
import model.dao.MovimentacaoBancariaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerConta {
    
    public static String insert(String use, int tipo, char v){
        Conta conta = new Conta();
        ContaDAO contaDao = new ContaDAO();     
        String s = null;
        if(v == 'N'){
            Random random = new Random();
            int n1 = random.nextInt(9);
            int n2 = random.nextInt(9);
            int n3 = random.nextInt(9);
            int n4 = random.nextInt(9);
            int dv = random.nextInt(9);
            s = ""+n1+"."+n2+n3+n4+"-"+dv; 
        }else if(v == 'M')
            s = contaDao.select("usuario", use).get(0).getNumeroDaConta();
        conta.setNumeroDaConta(s);
        conta.setUsuario(use);
        conta.setAgencia("0001");
        Date hoje = new Date();
        conta.setAbertura(hoje);
        conta.setTipo(tipo);
        switch (tipo){
            case 2:
                conta.setQtdTransacoes(10);
                conta.setLimiteTeds(1500);
                conta.setQtdSaques(20);
                conta.setLimiteSaques(2000);
                break;
            case 4:
                conta.setQtdTransacoes(7);
                conta.setLimiteTeds(1000);
                conta.setQtdSaques(15);
                conta.setLimiteSaques(1500);
                break;
            case 3:
                conta.setQtdTransacoes(15);
                conta.setLimiteTeds(3000);
                conta.setQtdSaques(50);
                conta.setLimiteSaques(5000);
                break;
            case 5:
                conta.setQtdTransacoes(10);
                conta.setLimiteTeds(1500);
                conta.setQtdSaques(20);
                conta.setLimiteSaques(2000);
                break;
        }
        if(v == 'N'){
            ArrayList<Conta> list;
            list = contaDao.select("numero_da_conta", conta.getNumeroDaConta());
            if (!list.isEmpty())
                for(int i = 0; i<list.size(); i++)
                    if(list.get(i).getTipo()%2 == conta.getTipo()%2)
                        insert(use, tipo, 'N');        
            if(contaDao.insert(conta))
                return s;
            else 
                return null;
        }else{
            if(contaDao.insert(conta))
                return s;
            else 
                return null;
        }
        
    }
    
    public static ArrayList<Conta> select(String use){
        ContaDAO conta = new ContaDAO();
        ArrayList<Conta> contas = conta.select("usuario", use);
        return contas;
    }
    
    public static Conta confirmaConta(String numConta, int tipo){
        ContaDAO contaDao = new ContaDAO();
        ArrayList<Conta> contas = contaDao.select("numero_da_conta", numConta);
        for (Conta conta : contas) {
            if(conta.getTipo()== tipo)
                return conta;
        }
        return null;
    }
    
    public static float atualizaSaldo(int id){
        float val = 0;
        MovimentacaoBancariaDAO mbd = new MovimentacaoBancariaDAO();
        ArrayList<MovimentacaoBancaria> mb = mbd.load(id);
        for (MovimentacaoBancaria mb1 : mb) 
            val += mb1.getValor();
        return val;
    }
    
    public static String nomeUse(String use){
        ContaDAO conta = new ContaDAO();
        if(use.length() < 15)
            return conta.pegaNomeUsePf(use);
        else 
            return conta.pegaNomeUsePJ(use);
    }
    
    public static boolean verificaData(int dia, int mes, int ano){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if(ano == year)
            return(month > mes && dia == day);
        else
            return (dia == day);                        
    }
        
    public static void atualizaQtds(int id){
        ContaDAO contaDao = new ContaDAO();
        Conta conta = contaDao.load(id);
        String data = conta.getAbertura().toString();
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(6, 7));
        int dia = Integer.parseInt(data.substring(8, 10));            
        if(verificaData(dia,mes,ano)){
            switch (conta.getTipo()){
                case 2:
                    conta.setQtdTransacoes(7);
                    conta.setQtdSaques(15);                
                    break;
                case 4:
                    conta.setQtdTransacoes(10);
                    conta.setQtdSaques(20);
                    break;
                case 3:
                    conta.setQtdTransacoes(15);
                    conta.setQtdSaques(50);
                    break;
                case 5:
                    conta.setQtdTransacoes(10);
                    conta.setQtdSaques(20);
                    break;
            }
            contaDao.update(conta);
        }
    }
    
    public static boolean deleteAll(String use){
        ContaDAO conta = new ContaDAO();
        return conta.deleteAll(use);
    }
    
    public static Conta load(int id){
        ContaDAO contaDao = new ContaDAO();
        return contaDao.load(id);
    }
    
    public static boolean update(Conta conta){
        ContaDAO contaDao = new ContaDAO();
        return contaDao.update(conta);
    }
            
}
