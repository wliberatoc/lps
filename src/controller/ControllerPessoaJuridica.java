/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import model.classes.PessoaJuridica;
import model.dao.PessoaJuridicaDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerPessoaJuridica {
    
    public static boolean insert(String cnpj, String nome, Date dataFund, String email, String telefone, String endereco, int senha, String senhaLogin){
        PessoaJuridica cliente = new PessoaJuridica();
        cliente.setCnpj(cnpj);
        cliente.setNome(nome);
        cliente.setFundacao(dataFund);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setSenha(senha);
        cliente.setSenhaLogin(senhaLogin);
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        return clienteDao.insert(cliente);  
    }//fim cadastra Pessoa Juridica
    
    public static boolean update(String cnpj, String nome, Date dataFund, String email, String telefone, String endereco){
        PessoaJuridica cliente = new PessoaJuridica();
        cliente.setCnpj(cnpj);
        cliente.setNome(nome);
        cliente.setFundacao(dataFund);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setId((int)select(cnpj)[0]);
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        return clienteDao.insert(cliente);  
    }//fim cadastra Pessoa Juridica
    
    public static Object [] select(String cnpj){
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        PessoaJuridica lista = clienteDao.select(cnpj);
        Object [] cliente  = new Object[7];
        cliente[0] = ((PessoaJuridica) lista).getId();
        cliente[1] = ((PessoaJuridica) lista).getCnpj();
        cliente[2] = ((PessoaJuridica) lista).getNome();
        cliente[3] = ((PessoaJuridica) lista).getFundacao();
        cliente[4] = ((PessoaJuridica) lista).getEmail();
        cliente[5] = ((PessoaJuridica) lista).getTelefone();
        cliente[6] = ((PessoaJuridica) lista).getEndereco();
        return cliente;
    }
    
    public static boolean login(String cnpj, String senha){
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        String senhaBD = clienteDao.select(cnpj).getSenhaLogin();
        return senhaBD.equals(senha);
    }
    
    public static boolean confirma(String cnpj, int senha){
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        int senhaBD = clienteDao.select(cnpj).getSenha();
        return senhaBD==senha;
    }
    
    public static boolean delete(String use){
        PessoaJuridicaDAO clienteDao = new PessoaJuridicaDAO();
        return clienteDao.delete(use);
    }
}
