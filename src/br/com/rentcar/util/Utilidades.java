/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author sala308b
 */
public class Utilidades {
    
     public static double removerMascaraMoeda(String valor){
        valor = valor.replace("R$", "").replace(".", "").replace(",", ".");
        
        return Double.parseDouble(valor);
    }
     
    public static String formataValor(Double valor) {
        //PARA MOEDAS
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(valor);
    }

    
    public static void AbrirArquivo(String nomeArquivo){
        
        Desktop desk = null;
        File arq = new File(nomeArquivo);
        
        if(Desktop.isDesktopSupported()){
            desk = Desktop.getDesktop();
        }
        if(desk != null){
            try {
                desk.open(arq);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    public static String converterCalendarParaString(Calendar data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = simpleDateFormat.format(data.getTime());
        return dataFormatada;
    }
    
    public static Calendar converterStringParaCalendar(String data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataFormatada = Calendar.getInstance();
        dataFormatada.setTime(simpleDateFormat.parse(data));
        return dataFormatada;
    }
    
     public static void setaIconeJFrame(JFrame form){
        ImageIcon icone = new ImageIcon(form.getClass().getResource("/br/com/rentcar/imagens/logo-carro.png")); 
        form.setIconImage(icone.getImage());            
    }
     
    public static int calculaDiasEntreDatas(Calendar data1, Calendar data2){        
        data1.set(Calendar.HOUR_OF_DAY, 00);  
        data1.set(Calendar.MINUTE, 00);  
        data1.set(Calendar.SECOND, 00);  
        data1.set(Calendar.MILLISECOND, 00);
        
        data2.set(Calendar.HOUR_OF_DAY, 00);  
        data2.set(Calendar.MINUTE, 00);  
        data2.set(Calendar.SECOND, 00);  
        data2.set(Calendar.MILLISECOND, 00);
                                  
        long d1 = data1.getTimeInMillis();  
        long d2 = data2.getTimeInMillis();
        int dias = (int)((d2 - d1)/ (24*60*60*1000));

        return dias;
    }     

}

