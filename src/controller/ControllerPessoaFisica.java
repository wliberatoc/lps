/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import model.classes.PessoaFisica;
import model.dao.PessoaFisicaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerPessoaFisica {
  
    public static boolean cadastraPessoaFisica(String cpf, String nome, Date dataNasc, char sexo, String email, String telefone, String endereco, int senha, String senhaLogin){
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf(cpf);
        pf.setNome(nome);
        pf.setNascimento(dataNasc);
        pf.setSexo(sexo);
        pf.setEmail(email);
        pf.setTelefone(telefone);
        pf.setEndereco(endereco);
        pf.setSenha(senha);
        pf.setSenhaLogin(senhaLogin);
        PessoaFisicaDAO cliente = new PessoaFisicaDAO();
        return cliente.insert(pf);  
    }//fim cadastra Pessoa Fisica
    
    public boolean editarPessoaFisica(String cpf, String nome, Date dataNasc, char sexo, String email, String telefone, String endereco, int senha, String senhaLogin, int id){
        PessoaFisica pf = new PessoaFisica();
        pf.setId(id);
        pf.setCpf(cpf);
        pf.setNome(nome);
        pf.setNascimento(dataNasc);
        pf.setSexo(sexo);
        pf.setEmail(email);
        pf.setTelefone(telefone);
        pf.setEndereco(endereco);
        pf.setSenha(senha);
        pf.setSenhaLogin(senhaLogin);
        PessoaFisicaDAO cliente = new PessoaFisicaDAO();
        return cliente.update(pf);  
    }
    
    public static ArrayList<PessoaFisica> selecionarPessoaFisica(String cpf){
        PessoaFisicaDAO cliente = new PessoaFisicaDAO();
        ArrayList<PessoaFisica> clientes = cliente.select("cpf", cpf);
        return clientes;
    }
    
    public static boolean login(String cpf, String senha){
        PessoaFisicaDAO cliente = new PessoaFisicaDAO();
        ArrayList<PessoaFisica> clientes = cliente.select("cpf", cpf);
        return clientes.get(0).getSenhaLogin().equals(senha);
    }
    
}
