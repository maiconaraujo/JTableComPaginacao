
package br.com.rentcar.dao;

import br.com.rentcar.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    private Connection conexao;
    
    public ClienteDAO() {
        conexao = ConnectionFactory.getConnection();
    }
    
    public boolean inserirCliente(Cliente cliente) {
        
        boolean resultado = false;
        
        String sql = "insert into clientes (cpf, nome, endereco, sexo, telefone, celular) values (?,?,?,?,?,?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getSexo());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            
            stmt.executeUpdate();
            stmt.close();
            
            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return resultado;
    }
    
    
    //buscando dados da tabela para exibir no formulário
    public ArrayList<Cliente> getlist() {
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from clientes order by nome";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //quando abre o rs, ele não vai pro primeiro registro. é necessário usar o rs.next() para que ele vá para o primeiro e, depois, para os próximos
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                
                listaClientes.add(cliente);
            }
            stmt.close();
            rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return listaClientes;
                
    }
    

    public ArrayList<Cliente> getlistByNome(String nome) {
        
        nome = "%" + nome.trim() + "%";
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from clientes where nome like ? order by nome";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            //quando abre o rs, ele não vai pro primeiro registro. é necessário usar o rs.next() para que ele vá para o primeiro e, depois, para os próximos
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                
                listaClientes.add(cliente);
            }
            stmt.close();
            rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return listaClientes;
                
    }


    
    public boolean atualizaCliente(Cliente cliente) {
        
        boolean resultado = false;
        
        String sql = "update clientes set nome = ?, endereco = ?, sexo = ?, telefone = ?, celular = ? where cpf = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getCpf());

            stmt.executeUpdate();
            stmt.close();
            
            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return resultado;
    }
    
    
    public boolean removeCliente(Cliente cliente) {
        
        boolean resultado = false;
        
        String sql = "delete from clientes where cpf = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());

            stmt.executeUpdate();
            stmt.close();
            
            resultado = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return resultado;
    }
    
    
}
