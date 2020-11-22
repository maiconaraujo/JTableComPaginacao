
package br.com.rentcar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import br.com.rentcar.model.Cliente;
import br.com.rentcar.model.Veiculo;
import br.com.rentcar.model.Locacao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala305b
 */
public class LocacaoDAO {
    
    private Connection conexao;
    
    public LocacaoDAO() {
        conexao = ConnectionFactory.getConnection();
    }
    
    public boolean inserirLocacao(Locacao locacao) {
        
        boolean resultado = false;
        
        String sql = "insert into locacoes (data_locacao, qtd_dias, observacao, valor_locacao, cpf_cliente, chassi_veiculo) "
                + "values (?,?,?,?,?,?)";
        
        try {
            //para não cofirmar a inserção se der algum erro
            conexao.setAutoCommit(false);
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            Date dataLocacao = new Date(locacao.getDataLocacao().getTimeInMillis());
            stmt.setDate(1, dataLocacao);
            stmt.setInt(2, locacao.getQtdDiasLocacao());
            stmt.setString(3, locacao.getObservacao());
            stmt.setDouble(4, locacao.getValorlocacao());
            stmt.setString(5, locacao.getCliente().getCpf());
            stmt.setString(6, locacao.getVeiculo().getChassi());

            stmt.executeUpdate();                        
            stmt.close();
            
            //criando VeiculoDAO para alterar a situacao do veiculo para alugado
            VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
            Veiculo veiculo = locacao.getVeiculo();
            veiculo.setSituacao("Alugado");
            
            //chamando o método para alterar a situação do ve
            boolean alterado = veiculoDAO.alterarSituacaoVeiculo(veiculo);
            if(alterado == true){
                conexao.commit();
                resultado = true;
            }                       

        } catch (SQLException ex) {
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null,"Erro ao efetuar rollback da locação - " + ex1.getMessage());
            }
            JOptionPane.showMessageDialog(null,"Erro ao salvar locação - " + ex.getMessage());
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return resultado;
    }
    
    public boolean registrarDevolucao(Locacao locacao) {
        
        boolean resultado = false;
        
        String sql = "UPDATE locacoes SET data_devolucao = ? " + 
                "WHERE codigo = ?";
        
        try {
            //para não cofirmar a inserção se der algum erro
            conexao.setAutoCommit(false);
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            Date dataDevolucao = new Date(locacao.getDataDevolucao().getTimeInMillis());
            stmt.setDate(1, dataDevolucao);
            stmt.setInt(2, locacao.getCodigo());
           

            stmt.executeUpdate();                        
            stmt.close();
            
            //criando VeiculoDAO para alterar a situacao do veiculo para alugado
            VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
            Veiculo veiculo = locacao.getVeiculo();
            veiculo.setSituacao("Disponível");
            
            //chamando o método para alterar a situação do ve
            boolean alterado = veiculoDAO.alterarSituacaoVeiculo(veiculo);
            if(alterado == true){
                conexao.commit();
                resultado = true;
            }                       

        } catch (SQLException ex) {
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null,"Erro ao efetuar rollback da locação - " + ex1.getMessage());
            }
            JOptionPane.showMessageDialog(null,"Erro ao registrar devolução do veículo - " + ex.getMessage());
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return resultado;
    }    

    //buscando dados da tabela locacoes para exibir no formulário
    public ArrayList<Locacao> getlist() {
        
        ArrayList<Locacao> locacoes = new ArrayList<>();
        String sql = "select c.*, l.*, v.* " +
                     "from clientes c, locacoes l, veiculos v " +
                     "where l.cpf_cliente = c.cpf " +
                     "and l.chassi_veiculo = v.chassi " +
                     "order by l.data_locacao desc";
        
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //quando cria o rs, ele não vai pro primeiro registro. é necessário usar o rs.next() para que ele vá para o primeiro e, depois, para os próximos
            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("c.cpf"));
                cliente.setNome(rs.getString("c.nome"));
                cliente.setEndereco(rs.getString("c.endereco"));
                cliente.setSexo(rs.getString("c.sexo"));
                cliente.setTelefone(rs.getString("c.telefone"));
                cliente.setCelular(rs.getString("c.celular"));

                Veiculo veiculo = new Veiculo();
                veiculo.setChassi(rs.getString("v.chassi"));
                veiculo.setPlaca(rs.getString("v.placa"));
                veiculo.setMarca(rs.getString("v.marca"));
                veiculo.setModelo(rs.getString("v.modelo"));
                veiculo.setAnoFabricacao(Integer.parseInt(rs.getString("v.ano_fabricacao")));
                veiculo.setValorLocacao(Double.parseDouble(rs.getString("v.valor_locacao")));
                veiculo.setSituacao(rs.getString("v.situacao"));

                Locacao locacao = new Locacao();
                locacao.setCliente(cliente);
                locacao.setVeiculo(veiculo);
                locacao.setObservacao(rs.getString("l.observacao"));
                locacao.setValorlocacao(Double.parseDouble(rs.getString("l.valor_locacao")));
                locacao.setQtdDiasLocacao(rs.getInt("l.qtd_dias"));
                
                
                try{
                    Calendar dataLoc = Calendar.getInstance();
                    dataLoc.setTime(rs.getDate("l.data_locacao"));
                    locacao.setDataLocacao(dataLoc);
                    
                }catch(Exception ex){
                    
                }
                
                locacoes.add(locacao);
            }
            stmt.close();
            rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no acesso ao banco de dados - " + ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão - " + ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return locacoes;
                
    }
    
    
}
