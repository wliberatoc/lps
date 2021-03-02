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
public abstract class Conta {
    private int id;
    protected String numeroDaConta;
    protected String agencia;
    protected char tipo;
    protected float saldo;
    protected int teds;
    protected float limiteTeds;
    protected int saques;
    protected float limiteSaques;
    protected Date abertura;
    

    public Conta() {
        this.numeroDaConta = "";
        this.agencia = "";
        this.tipo = ' ';
        this.saldo = 0;
        this.limiteSaques = 0;
        this.limiteTeds = 0;
        this.teds = 0;
        this.saques = 0;
        this.abertura = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public int getTeds() {
        return teds;
    }

    public void setTeds(int teds) {
        this.teds = teds;
    }

    public float getLimiteTeds() {
        return limiteTeds;
    }

    public void setLimiteTeds(int limiteTeds) {
        this.limiteTeds = limiteTeds;
    }

    public int getSaques() {
        return saques;
    }

    public void setSaques(int saques) {
        this.saques = saques;
    }

    public float getLimiteSaques() {
        return limiteSaques;
    }

    public void setLimiteSaques(int limiteSaques) {
        this.limiteSaques = limiteSaques;
    }

    public void setNumeroDaConta(String numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }


    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setLimiteTeds(float limiteTeds) {
        this.limiteTeds = limiteTeds;
    }

    public void setLimiteSaques(float limiteSaques) {
        this.limiteSaques = limiteSaques;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
     
}
