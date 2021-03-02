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
public class ContaPJ extends Conta{
    private String cnpjTitular;

    public ContaPJ() {
        this.cnpjTitular = "";
    }

    public String getCnpjTitular() {
        return cnpjTitular;
    }

    public void setCnpjTitular(String cnpjTitular) {
        this.cnpjTitular = cnpjTitular;
    }

    @Override
    public void setSaldo(float saldo) {
        super.setSaldo(saldo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLimiteSaques(float limiteSaques) {
        super.setLimiteSaques(limiteSaques); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLimiteTeds(float limiteTeds) {
        super.setLimiteTeds(limiteTeds); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getSaldo() {
        return super.getSaldo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipo(char tipo) {
        super.setTipo(tipo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAgencia(String agencia) {
        super.setAgencia(agencia); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAgencia() {
        return super.getAgencia(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNumeroDaConta(String numeroDaConta) {
        super.setNumeroDaConta(numeroDaConta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLimiteSaques(int limiteSaques) {
        super.setLimiteSaques(limiteSaques); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getLimiteSaques() {
        return super.getLimiteSaques(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSaques(int saques) {
        super.setSaques(saques); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSaques() {
        return super.getSaques(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLimiteTeds(int limiteTeds) {
        super.setLimiteTeds(limiteTeds); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getLimiteTeds() {
        return super.getLimiteTeds(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTeds(int teds) {
        super.setTeds(teds); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTeds() {
        return super.getTeds(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNumeroDaConta() {
        return super.getNumeroDaConta(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAbertura(Date abertura) {
        super.setAbertura(abertura); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getAbertura() {
        return super.getAbertura(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

        
}
