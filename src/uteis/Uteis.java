/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.util.Calendar;

/**
 *
 * @author Willian-PC
 */
public final class Uteis {
    public static boolean verificaCPF(String cpf){
        int calc1=0, calc2=0, aux1=10, aux2=11;
        int [] arrayCPF;
        boolean repetido = true;
        arrayCPF = new int[9];
        int dig1 = Integer.parseInt(cpf.substring(12,13));
        int dig2 = Integer.parseInt(cpf.substring(13,14));
        cpf = cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11);
        for(int i=0; i<arrayCPF.length; i++){
            arrayCPF[i] = Integer.parseInt(cpf.substring(i, i+1));
            calc1 += aux1 * arrayCPF[i];
            aux1--;
            calc2 += aux2 * arrayCPF[i];
            aux2--;            
            if(arrayCPF[0] != arrayCPF[i] && repetido)
                repetido = false;
        }
        calc2 += dig1 * aux2;
        calc1 = (calc1 * 10) % 11;
        calc2 = (calc2 * 10) % 11;
        if(calc1 == 10)
            calc1 = 0;
        if(calc2 == 10)
            calc2 = 0;             
        return(calc1 == dig1 && calc2 == dig2 && !repetido);
    }//fim função verifica CPF
    
    public static boolean verificaCNPJ(String cnpj){
        int calc1=0, calc2=0, aux=1;
        int [] arrayNumsCalc = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        int dig1 = Integer.parseInt(cnpj.substring(16,17));
        int dig2 = Integer.parseInt(cnpj.substring(17,18));
        cnpj = cnpj.substring(0,2) + cnpj.substring(3,6) + cnpj.substring(7,10) + cnpj.substring(11,15);
        for(int i=0; i<cnpj.length(); i++){
            calc1 += Integer.parseInt(cnpj.substring(i, i+1)) * arrayNumsCalc[aux];
            if(aux < 12)
                calc2 += Integer.parseInt(cnpj.substring(aux, aux+1)) * arrayNumsCalc[aux];
            aux++;
        }
        calc1 = calc1% 11;
        if(calc1 < 2)
            calc1 = 0;
        else
            calc1 = 11 - calc1;
        
        calc2 += (dig1 * 2) + (Integer.parseInt(cnpj.substring(0, 1)) * 6 );
        calc2 = calc2% 11;
        if(calc2 < 2)
            calc2 = 0;
        else
            calc2 = 11 - calc2;
        return(calc1 == dig1 && calc2 == dig2);
    }//fim função verifica CNPJ
    
    public static boolean verificaData(String data){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int ano, mes, dia;
        dia = Integer.parseInt(data.substring(0,2));
        mes = Integer.parseInt(data.substring(3,5));
        ano = Integer.parseInt(data.substring(6,10));
        if(ano < 1694 || ano > year)
            return false;
        if(mes > 12 || mes < 1)
            return false;
        if((mes == 2 && (ano % 400 == 0)) && dia > 29)
            return false;
        if((mes == 2 && (ano % 400 != 0)) && dia > 28)
            return false;
        if(((mes < 8 && mes%2 == 0) || (mes > 7 && mes%2 != 0)) && dia > 30)
            return false;
        return(dia > 0 || dia < 32);
    }//fim verifica data
    
    public static boolean verificaSeTem18Anos(String data){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int ano, mes, dia;
        dia = Integer.parseInt(data.substring(0,2));
        mes = Integer.parseInt(data.substring(3,5));
        ano = Integer.parseInt(data.substring(6,10));
        if((year - ano) < 18)
            return false;
        else if((year - ano) == 18 && mes < month)
            return false;
        else if((year - ano) == 18 && mes == month && dia < day)
            return false;
        return true;
    }//fim verifica se tem 18 
    
}//fim classe uteis
