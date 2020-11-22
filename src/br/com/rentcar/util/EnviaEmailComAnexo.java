/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.mail.EmailAttachment;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EnviaEmailComAnexo {

    public static void main(String[] args) {
        try {            
            //criando um objeto da classe JFileChooser para abrir uma caixa de diálogo para escolher arquivo
            JFileChooser fileChooser = new JFileChooser();
            
            //setando a opção de escolher apenas arquivos e não pastas
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            //criando um filtro para aceitar apenas extensões doc e dodcx
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos", "doc", "docx");

            //adicionando o filtro de extensões no objeto fileChooser
            fileChooser.addChoosableFileFilter(filter);

            //setando a opção para remover a opção de escolher todos os arquivos
            fileChooser.setAcceptAllFileFilterUsed(false);

            /*chamando o método showOpenDialog para abrir a janela
            ao escolher um arquivo ou clicar em cancelar um código de
            retorno ficará guardado na variável returnValue*/
            int returnValue = fileChooser.showOpenDialog(fileChooser);

            /*se o código de retorno for diferente de 0 (JFileChooser.APPROVE_OPTION)
             o método é encerrado e restante do código abaixo não será executado
             */
            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File origem = fileChooser.getSelectedFile();
            
            //Criando o anexo
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(origem.getPath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);      
            attachment.setName(origem.getName());

            // Create the email message
            HtmlEmail email = new HtmlEmail();            
            email.addTo("destinatario@gmail.com", "Nome destinatario"); //destinatário
            email.setFrom("remetente@gmail.com", "Nome Remetente"); //remetente
            email.setSubject("Arquivos"); //assunto
            email.setHtmlMsg("<strong> Olá Mundo! </strong>"); //mensagem
            
            email.setHostName("smtp.gmail.com");
            email.setSSLOnConnect(true);
            email.setSmtpPort(465);

            String user = "user@gmail.com"; //usuario de email do gmail usado para enviar
            String pass = "senha"; //senha do email

            email.setAuthentication(user, pass);
            email.setDebug(true);

            // add the attachment
            email.attach(attachment);            

            // send the email
            email.send();
            
            JOptionPane.showMessageDialog(null, "Email enviado!");
        } catch (EmailException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar email : " + ex.getMessage());
        }
    }

}
