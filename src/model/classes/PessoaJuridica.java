/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classes;

import java.util.Date;

/**
 *
 * @author Willian-PC
 */
public class PessoaJuridica extends Cliente{
    private String cnpj;
    private Date fundacao;

    public PessoaJuridica() {
        this.cnpj = "";
        this.fundacao = null;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getFundacao() {
        return fundacao;
    }

    public void setFundacao(Date fundacao) {
        this.fundacao = fundacao;
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void setSenhaLogin(String senhaLogin) {
        super.setSenhaLogin(senhaLogin); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSenhaLogin() {
        return super.getSenhaLogin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSenha(int senha) {
        super.setSenha(senha); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSenha() {
        return super.getSenha(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTelefone(String telefone) {
        super.setTelefone(telefone); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTelefone() {
        return super.getTelefone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndereco(String endereco) {
        super.setEndereco(endereco); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndereco() {
        return super.getEndereco(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        return super.getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
