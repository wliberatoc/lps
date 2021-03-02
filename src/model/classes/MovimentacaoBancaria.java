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
public class MovimentacaoBancaria {
    String numeroDaConta;
    String agencia;
    char tipoDaConta;
    double valor;
    Date data;
    char tipoDaOperacao;
    String descricao;

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

    public char getTipoDaConta() {
        return tipoDaConta;
    }

    public void setTipoDaConta(char tipoDaConta) {
        this.tipoDaConta = tipoDaConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public char getTipoDaOperacao() {
        return tipoDaOperacao;
    }

    public void setTipoDaOperacao(char tipoDaOperacao) {
        this.tipoDaOperacao = tipoDaOperacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MovimentacaoBancaria() {
        this.numeroDaConta = "";
        this.agencia = "";
        this.tipoDaConta = ' ';
        this.valor = 0;
        this.data = null;
        this.tipoDaOperacao = ' ';
        this.descricao = "";
    }
    
}