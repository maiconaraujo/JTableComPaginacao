/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.rentcar.model.Usuario;

/**
 *
 * @author sala308b
 */
public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public boolean insert(Usuario usuario) {
        String sql = "insert into usuarios(nome, login, senha) "
                + "values(?,?,?)";

        boolean resultado = false;

        try {
            this.connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            this.connection.commit();
            resultado = true;

        } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                this.connection.rollback();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex1.printStackTrace();
            }
        } finally {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        return resultado;
    }

     public Usuario getUsuarioByLoginAndSenha(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        Usuario usuario = null;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        return usuario;
    }
}
