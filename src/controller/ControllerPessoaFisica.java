/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import model.classes.PessoaFisica;
import model.dao.PessoaFisicaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerPessoaFisica {
  
    public static boolean insert(String cpf, String nome, Date dataNasc, char sexo, String email, String telefone, String endereco, int senha, String senhaLogin){
        PessoaFisica cliente = new PessoaFisica();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setNascimento(dataNasc);
        cliente.setSexo(sexo);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setSenha(senha);
        cliente.setSenhaLogin(senhaLogin);
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        return clienteDao.insert(cliente);  
    }//fim cadastra Pessoa Fisica
    
    public static boolean update(String cpf, String nome, Date dataNasc, char sexo, String email, String telefone, String endereco){
        PessoaFisica cliente = new PessoaFisica();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setNascimento(dataNasc);
        cliente.setSexo(sexo);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setId((int)select(cpf)[0]);
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        return clienteDao.update(cliente);  
    }
    
    public static Object [] select(String cpf){
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        PessoaFisica lista = clienteDao.select(cpf);
        Object [] cliente  = new Object[8];
        cliente[0] = ((PessoaFisica) lista).getId();
        cliente[1] = ((PessoaFisica) lista).getCpf();
        cliente[2] = ((PessoaFisica) lista).getNome();
        cliente[3] = ((PessoaFisica) lista).getNascimento();
        cliente[4] = ((PessoaFisica) lista).getSexo();
        cliente[5] = ((PessoaFisica) lista).getEmail();
        cliente[6] = ((PessoaFisica) lista).getTelefone();
        cliente[7] = ((PessoaFisica) lista).getEndereco();
        return cliente;
    }
    
    public static boolean login(String cpf, String senha){
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        String senhaBD = clienteDao.select(cpf).getSenhaLogin();
        return senhaBD.equals(senha);
    }
    
    public static boolean confirma(String cpf, int senha){
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        int senhaBD = clienteDao.select(cpf).getSenha();
        return senhaBD==senha;
    }       
    
    public static boolean delete(String use){
        PessoaFisicaDAO clienteDao = new PessoaFisicaDAO();
        return clienteDao.delete(use);
    }
}
