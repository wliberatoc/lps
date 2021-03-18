/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import model.classes.Conta;
import model.dao.ContaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerConta {
    public static String cadastraConta(String cpf, char tipo){
        Conta conta = new Conta();
        Random random = new Random();
        int n1 = random.nextInt(9);
        int n2 = random.nextInt(9);
        int n3 = random.nextInt(9);
        int n4 = random.nextInt(9);
        int dv = random.nextInt(9);
        String s = ""+n1+"."+n2+n3+n4+"-"+dv;
        conta.setNumeroDaConta(s);
        conta.setUsuario(cpf);
        conta.setAgencia("0001");
        Date hoje = new Date();
        conta.setAbertura(hoje);
        if(tipo == 'P'){
            conta.setTipo(4);
            conta.setQtdTransacoes(7);
            conta.setLimiteTeds(1000);
            conta.setQtdSaques(15);
            conta.setLimiteSaques(1500);
        }
        if(tipo == 'C'){
            conta.setTipo(2);
            conta.setQtdTransacoes(10);
            conta.setLimiteTeds(1500);
            conta.setQtdSaques(20);
            conta.setLimiteSaques(2000);
        }  
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> list;
        list = contaDAO.select("numero_da_conta", conta.getNumeroDaConta());
        if (!list.isEmpty())
            for(int i = 0; i<list.size(); i++)
                if(list.get(i).getTipo()%2 == conta.getTipo()%2)
                    cadastraConta(cpf, tipo);        
        if(contaDAO.insert(conta))
            return s;
        else 
            return null;
    }
}
