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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import br.com.rentcar.model.Veiculo;

/**
 *
 * @author sala305b
 */
public class VeiculoDAO {

    private Connection conexao;

    public VeiculoDAO() {
        conexao = ConnectionFactory.getConnection();
    }

    public VeiculoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    

    public boolean inserirVeiculo(Veiculo veiculo) {

        boolean resultado = false;

        String sql = "INSERT INTO veiculos(chassi, placa, marca, modelo, ano_fabricacao, valor_locacao, situacao) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getChassi());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getMarca());
            stmt.setString(4, veiculo.getModelo());
            stmt.setInt(5, veiculo.getAnoFabricacao());
            stmt.setDouble(6, veiculo.getValorLocacao());
            stmt.setString(7, veiculo.getSituacao());

            stmt.executeUpdate();
            stmt.close();

            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar um veículo: " + ex.getMessage());
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }

        return resultado;
    }

    public boolean alterarVeiculo(Veiculo veiculo) {

        boolean resultado = false;

        String sql = "UPDATE veiculos SET placa=?, marca=?, modelo=?, "
                + "ano_fabricacao=?, valor_locacao=?, situacao=? "
                + "WHERE chassi=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAnoFabricacao());
            stmt.setDouble(5, veiculo.getValorLocacao());
            stmt.setString(6, veiculo.getSituacao());
            stmt.setString(7, veiculo.getChassi());

            stmt.executeUpdate();
            stmt.close();

            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar um veículo: " + ex.getMessage());
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }

        return resultado;
    }
    
    public boolean alterarSituacaoVeiculo(Veiculo veiculo) {

        boolean resultado = false;

        String sql = "UPDATE veiculos SET situacao=? "
                + "WHERE chassi=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getSituacao());
            stmt.setString(2, veiculo.getChassi());

            stmt.executeUpdate();
            stmt.close();

            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar um veículo: " + ex.getMessage());
        } 

        return resultado;
    }    
    
    
    //retorna a quantidade total de registros
    public int getCountVeiculos() {
        int contador = 0;

        String sql = "SELECT count(*) as qtd FROM veiculos";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contador = rs.getInt("qtd");
            }
            stmt.close();
            rs.close();
            conexao.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return contador;
    }

    public boolean excluirVeiculo(Veiculo veiculo) {

        boolean resultado = false;

        String sql = "DELETE FROM veiculos WHERE chassi=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getChassi());

            stmt.executeUpdate();
            stmt.close();

            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir um veículo: " + ex.getMessage());
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }

        return resultado;
    }


    public ArrayList<Veiculo> listarVeiculosPaginacao(int posicao, int count) {
        String sql = "SELECT * FROM veiculos ORDER BY situacao, marca LIMIT ?,?";
        ArrayList<Veiculo> listaDeVeiculos = new ArrayList<>();

        try {
            if (conexao.isClosed()) {
                conexao = ConnectionFactory.getConnection();
            }

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, posicao);
            stmt.setInt(2, count);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo objetoVeiculo = new Veiculo();
                objetoVeiculo.setChassi(rs.getString("chassi"));
                objetoVeiculo.setPlaca(rs.getString("placa"));
                objetoVeiculo.setMarca(rs.getString("marca"));
                objetoVeiculo.setModelo(rs.getString("modelo"));
                objetoVeiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
                objetoVeiculo.setValorLocacao(rs.getDouble("valor_locacao"));
                objetoVeiculo.setSituacao(rs.getString("situacao"));
                listaDeVeiculos.add(objetoVeiculo);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar veículos: " + ex.getMessage());

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }

        return listaDeVeiculos;

    }

    public ArrayList<Veiculo> listarVeiculosPorMarca(String marca) {
        marca = marca + "%";

        String sql = "SELECT * FROM veiculos WHERE marca like ?";
        ArrayList<Veiculo> listaDeVeiculos = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo objetoVeiculo = new Veiculo();
                objetoVeiculo.setChassi(rs.getString("chassi"));
                objetoVeiculo.setPlaca(rs.getString("placa"));
                objetoVeiculo.setMarca(rs.getString("marca"));
                objetoVeiculo.setModelo(rs.getString("modelo"));
                objetoVeiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
                objetoVeiculo.setValorLocacao(rs.getDouble("valor_locacao"));
                objetoVeiculo.setSituacao(rs.getString("situacao"));
                listaDeVeiculos.add(objetoVeiculo);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar veículos: " + ex.getMessage());

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }

        return listaDeVeiculos;

    }

}
