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
    private int id;
    private int idConta;
    private float valor;
    private Date data;
    private int idTipoTipoOperacao;
    private String descricao;
    private char tipoMovimentacao;

    public MovimentacaoBancaria() {
        this.valor = 0;
        this.data = null;
        this.descricao = "";
        this.tipoMovimentacao = ' ';
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdTipoOperacao() {
        return idTipoTipoOperacao;
    }

    public void setIdTipoOperacao(int idTipoTipoOperacao) {
        this.idTipoTipoOperacao = idTipoTipoOperacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(char tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }  
}
