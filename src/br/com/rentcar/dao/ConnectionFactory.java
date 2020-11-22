/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rentcar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sala305b
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/rentcar", "root", "123456");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com Banco de Dados: " + ex.getMessage());
        }
        
        return conexao;
        
    }    
}
