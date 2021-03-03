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
public class Boleto {
    private int id;
    private String codigoDeBarras;
    private int idConta;
    private float valor;
    private Date dataDeVencimento;

    public Boleto() {
        this.codigoDeBarras = "";
        this.valor = 0;
        this.dataDeVencimento = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
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

    public Date getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(Date dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
    
}
