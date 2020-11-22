/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.util;

import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EnviaEmail {

    public static void main(String[] args) {

        try {

            HtmlEmail email = new HtmlEmail();
            email.addTo("destinatario@gmail.com", "Nome destinatario"); //destinatário
            email.setFrom("remetente@gmail.com", "Nome Remetente"); //remetente
            email.setSubject("teste"); //assunto

            email.setHtmlMsg("<strong> Olá Mundo! </strong>"); //mensagem

            //configs do servidor
            // usando gmail
            email.setHostName("smtp.gmail.com");
            email.setSSLOnConnect(true);
            email.setSmtpPort(465);

            String user = "user@gmail.com"; //usuario de email do gmail usado para enviar
            String pass = "senha"; //senha do email

            email.setAuthentication(user, pass);
            email.setDebug(true);

            email.send(); //envia o email

            JOptionPane.showMessageDialog(null, "Email enviado!");
        } catch (EmailException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar email : " + ex.getMessage());
        }
    }

}
