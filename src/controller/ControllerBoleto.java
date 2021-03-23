/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import model.classes.Boleto;
import model.classes.Boleto;
import model.dao.BoletoDAO;

/**
 *
 * @author Willian-PC
 */
public class ControllerBoleto {
    
    public String criaData(){
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH)+1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        if(mes == 12 && dia > 28)
            return ""+(dia-27)+"/01/"+(ano+1);        
        else if((mes == 2 && (ano % 400 == 0)) && dia > 26)
            return ""+(dia-26)+"/"+(mes+1)+"/"+ano;
        else if((mes == 2 && (ano % 400 != 0)) && dia > 25)
            return ""+(dia-25)+"/"+(mes+1)+"/"+ano; 
        else if(((mes < 8 && mes%2 == 0)||(mes > 7 && mes%2 != 0)) && dia > 27)
            return ""+(dia-27)+"/"+(mes+1)+"/"+ano;
        else if(dia > 28)
            return ""+(dia-28)+"/"+(mes+1)+"/"+ano;       
        else
            return ""+(dia+3)+"/"+mes+"/"+ano;
    }//fim cria data
    
    public String geraCodigoBarras(String numConta, String tipo, String valor){
        BoletoDAO boletoDAO = new BoletoDAO();
        Random random = new Random();
        int dv = random.nextInt(9);
        String s = "00"+numConta.replace(".", "").replace("-", "")
                +"."+tipo+"000001.";
        for(int j = 0; j < 7; j++){
            int dig = random.nextInt(9);
            s += dig;
        }
        s += " "+dv+" ";
        int p = 7-valor.replace(".","").length();
        for(int k = 0; k < 7; k++){
            if(k == p){
                s += valor.replace(".","");
                break;
            }else
                s += "0";
        }
        if(boletoDAO.select(s) != null)
            return s;
        else
            geraCodigoBarras(numConta,tipo,valor);
        return null;
    }//fim gera codigo de barras
    
    public String[] geraBoleto(String numConta, String tipo, String valor,int id){
        String [] s = new String[2]; 
        BoletoDAO boletoDAO = new BoletoDAO();
        Boleto boleto = new Boleto();
        boleto.setIdConta(id);
        try{
            boleto.setValor(Float.parseFloat(valor));
            if(boleto.getValor() < 3 || boleto.getValor() > 9999999)
                return null;
            s[0] = geraCodigoBarras(numConta,tipo,valor);
            s[1] = criaData();
            try {
                SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                boleto.setCodigoDeBarras(s[0]);
                boleto.setDataDeVencimento(formataData.parse(s[1]));
                if(boletoDAO.insert(boleto))
                    return s;
                else
                    return null;
            }catch (ParseException ex) {
                System.err.println("Erro "+ex);
            }
        }catch (NumberFormatException ex) {
            return null;
        }
        return null; 
    }
    
    public static Object [] select(String cod){
        BoletoDAO boletoDao = new BoletoDAO();
        Boleto lista = boletoDao.select(cod);
        Object [] boleto  = new Object[6];
        boleto[0] = ((Boleto) lista).getId();
        boleto[1] = ((Boleto) lista).getCodigoDeBarras();
        boleto[2] = ((Boleto) lista).getIdConta();
        boleto[3] = ((Boleto) lista).getValor();
        boleto[4] = ((Boleto) lista).getDataDeVencimento();
        boleto[5] = ((Boleto) lista).isPago();
        return boleto;
    }
    
    public static boolean isVencido(String data){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(6, 8));
        int dia = Integer.parseInt(data.substring(9, 10));
        if(year > ano)
            return false;
        else if(month > mes)
            return false;
        return(ano == year && mes == month && dia < day);
    }
    
    public static boolean update(boolean pago, int id){
        BoletoDAO boletoDao = new BoletoDAO();
        Boleto boleto = new Boleto();
        boleto.setPago(pago);
        boleto.setId(id);
        return boletoDao.update(boleto);
    }
    
}
