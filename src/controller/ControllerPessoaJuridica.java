/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import model.classes.PessoaJuridica;
import model.dao.PessoaJuridicaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerPessoaJuridica {
    
    public static boolean cadastraPessoaJuridica(String cnpj, String nome, Date dataFund, String email, String telefone, String endereco, int senha, String senhaLogin){
        PessoaJuridica pf = new PessoaJuridica();
        pf.setCnpj(cnpj);
        pf.setNome(nome);
        pf.setFundacao(dataFund);
        pf.setEmail(email);
        pf.setTelefone(telefone);
        pf.setEndereco(endereco);
        pf.setSenha(senha);
        pf.setSenhaLogin(senhaLogin);
        PessoaJuridicaDAO cliente = new PessoaJuridicaDAO();
        return cliente.insert(pf);  
    }//fim cadastra Pessoa Juridica
    
    public static ArrayList<PessoaJuridica> selecionarPessoaJuridica(String cnpj){
        PessoaJuridicaDAO cliente = new PessoaJuridicaDAO();
        ArrayList<PessoaJuridica> clientes = cliente.select("cnpj", cnpj);
        return clientes;
    }
    
    public static boolean login(String cnpj, String senha){
        PessoaJuridicaDAO cliente = new PessoaJuridicaDAO();
        ArrayList<PessoaJuridica> clientes = cliente.select("cnpj", cnpj);
        return clientes.get(0).getSenhaLogin().equals(senha);
    }
}
