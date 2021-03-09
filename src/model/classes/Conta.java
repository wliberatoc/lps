/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classes;

import java.util.ArrayList;
import java.util.Date;
import model.dao.ContaDAO;
import model.dao.MovimentacaoBancariaDAO;

/**
 *
 * @author Willian-PC
 */
public class Conta {
    private int id;
    private String numeroDaConta;
    private String agencia;
    private int tipo;
    private float saldo;
    private int qtdTeds;
    private int qtdSaques;    
    private float limiteTeds;
    private float limiteSaques;
    private Date abertura;
    private String usuario;

    public Conta() {
        this.numeroDaConta = "";
        this.agencia = "";
        this.tipo = 0;
        this.saldo = 0;
        this.limiteSaques = 0;
        this.limiteTeds = 0;
        this.qtdTeds = 0;
        this.qtdSaques = 0;
        this.abertura = null;
        this.usuario = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getQtdTeds() {
        return qtdTeds;
    }

    public void setQtdTeds(int qtdTeds) {
        this.qtdTeds = qtdTeds;
    }

    public int getQtdSaques() {
        return qtdSaques;
    }

    public void setQtdSaques(int qtdSaques) {
        this.qtdSaques = qtdSaques;
    }

    public float getLimiteTeds() {
        return limiteTeds;
    }

    public void setLimiteTeds(float limiteTeds) {
        this.limiteTeds = limiteTeds;
    }

    public float getLimiteSaques() {
        return limiteSaques;
    }

    public void setLimiteSaques(float limiteSaques) {
        this.limiteSaques = limiteSaques;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public float atualizaSaldo(){
        float val = 0;
        MovimentacaoBancariaDAO mbd = new MovimentacaoBancariaDAO();
        ArrayList<MovimentacaoBancaria> mb = mbd.load(getId());
        for (MovimentacaoBancaria mb1 : mb) 
            val += mb1.getValor();
        return val;
    }
    
}
