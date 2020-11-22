/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.view;

import br.com.rentcar.dao.VeiculoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import br.com.rentcar.model.Veiculo;
import br.com.rentcar.model.VeiculoTableModel;
import br.com.rentcar.util.Utilidades;

/**
 *
 * @author sala304b
 */
public class FormListagemVeiculos extends javax.swing.JFrame {

    private VeiculoTableModel veiculoTableModel;

    /*
    - posicao guardará a partir de qual registro quero fazer a consulta.
    - qtdPorPagina guardará o valor de quantos registros 
    queremos trazer na consulta para mostrar na tabela*/
    int posicao = 0, qtdPorPagina = 3;

    public FormListagemVeiculos() {
        initComponents();
        Utilidades.setaIconeJFrame(this);
        this.setLocationRelativeTo(null);
        setResizable(false);
        prepararTable();
    }

    private void prepararTable() {
        VeiculoDAO dao = new VeiculoDAO();
        ArrayList<Veiculo> lista = new ArrayList<>();

        /*  Calculando o total de páginas:
            dividindo o total geral de registros pela qtd de registros por página            
         */
        float qtd = dao.getCountVeiculos() / (float) qtdPorPagina;

        /*arredondando para o próximo inteiro pois a divisão acima pode gerar número decimal*/
        int totalPaginas = (int) Math.ceil(qtd);

        //setando o valor total de páginas no campo txtTotalPaginas
        txtTotalPaginas.setText(String.valueOf(totalPaginas));

        /*se o totalPaginas for maior que zero 
        então é feita a consulta no banco para trazer os registros
         */
        if (totalPaginas > 0) {
            lista = dao.listarVeiculosPaginacao(posicao, qtdPorPagina);
            txtPagina.setText("1");

            /*se totalPagina for maior que 1 então
              habilita o botão próximo
             */
            if (totalPaginas > 1) {
                btnProximo.setEnabled(true);
            }
        }

        veiculoTableModel = new VeiculoTableModel(lista);
        tabelaVeiculos.setModel(veiculoTableModel);
        setarCaracteristicasTabela();
    }

    private void setarCaracteristicasTabela() {
        this.tabelaVeiculos.getColumnModel().getColumn(0).setMinWidth(150);
        this.tabelaVeiculos.getColumnModel().getColumn(0).setMaxWidth(150);

        //this.tabelaVeiculos.getColumnModel().getColumn(1).setMinWidth(120);
        //this.tabelaVeiculos.getColumnModel().getColumn(1).setMaxWidth(120);

        //this.tabelaVeiculos.getColumnModel().getColumn(2).setMinWidth(120);
        //this.tabelaVeiculos.getColumnModel().getColumn(2).setMaxWidth(120);

        this.tabelaVeiculos.getColumnModel().getColumn(3).setMinWidth(100);
        this.tabelaVeiculos.getColumnModel().getColumn(3).setMaxWidth(100);

        this.tabelaVeiculos.getColumnModel().getColumn(4).setMinWidth(150);
        this.tabelaVeiculos.getColumnModel().getColumn(4).setMaxWidth(150);

        this.tabelaVeiculos.getColumnModel().getColumn(5).setMinWidth(120);
        this.tabelaVeiculos.getColumnModel().getColumn(5).setMaxWidth(120);

        this.tabelaVeiculos.getColumnModel().getColumn(6).setMinWidth(80);
        this.tabelaVeiculos.getColumnModel().getColumn(6).setMaxWidth(80);

        //seleção para a linha inteira
        this.tabelaVeiculos.setCellSelectionEnabled(false);
        this.tabelaVeiculos.setRowSelectionAllowed(true);
        //selecionar apenas uma linha
        this.tabelaVeiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVeiculos = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotalPaginas = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPagina = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listagem de Veículos");

        jLabel2.setText("Marca");

        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        tabelaVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelaVeiculos);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jLabel3.setText("de");

        txtTotalPaginas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalPaginas.setText("0");
        txtTotalPaginas.setEnabled(false);

        btnAnterior.setText("anterior");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnProximo.setText("próximo");
        btnProximo.setEnabled(false);
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        jLabel4.setText("página");

        txtPagina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagina.setText("0");
        txtPagina.setEnabled(false);
        txtPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaginaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rentcar/imagens/dinheiro.gif"))); // NOI18N
        jButton1.setText("Locar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 466, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnterior)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProximo)
                .addGap(280, 280, 280))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnAnterior)
                    .addComponent(btnProximo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar)
                    .addComponent(btnAlterar)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        String pesquisa = txtPesquisa.getText().trim();

        if (pesquisa.equals("")) {
            JOptionPane.showMessageDialog(null, "Digite a placa para pesquisar!");
        } else {
            VeiculoDAO dao = new VeiculoDAO();
            ArrayList<Veiculo> lista = dao.listarVeiculosPorMarca(pesquisa);
            this.veiculoTableModel.addListaNova(lista);

            btnAnterior.setEnabled(false);
            btnProximo.setEnabled(false);
            txtPagina.setText("1");
            txtTotalPaginas.setText("1");
        }


    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int indice = this.tabelaVeiculos.getSelectedRow();

        if (indice >= 0) {
            Veiculo objetoVeiculo = this.veiculoTableModel.getVeiculo(indice);
            VeiculoDAO dao = new VeiculoDAO();
            boolean resultado = dao.excluirVeiculo(objetoVeiculo);

            if (resultado) {
                this.veiculoTableModel.removeVeiculo(indice);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um veículo");
        }


    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int indice = this.tabelaVeiculos.getSelectedRow();

        if (indice >= 0) {
            Veiculo objetoVeiculo = this.veiculoTableModel.getVeiculo(indice);
            FormVeiculo janela = new FormVeiculo(objetoVeiculo, this);
            janela.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um veículo");

        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        posicao = posicao - qtdPorPagina;
        int pagina = Integer.parseInt(txtPagina.getText()) - 1;
        txtPagina.setText(String.valueOf(pagina));

        consultar();

        //se estiver na primeira página, desabilita o botão Anterior
        if (pagina == 1) {
            btnAnterior.setEnabled(false);
        }

        //habilita o botão próximo
        btnProximo.setEnabled(true);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed

        /*calculando o num da posicao a ser pesquisada usando o LIMIT na consulta no banco
         */
        posicao = posicao + qtdPorPagina;

        /*pegando o número da página atual no campo txtPagina e somando + 1*/
        int pagina = Integer.parseInt(txtPagina.getText()) + 1;

        /*adicionando o novo valor do numero da página no campo txtPagina*/
        txtPagina.setText(String.valueOf(pagina));

        consultar();

        /*se chegou na última página, desabilita o botão Proximo*/
        if (pagina == Integer.parseInt(txtTotalPaginas.getText())) {
            btnProximo.setEnabled(false);
        }

        /*habilita o botão anterior*/
        btnAnterior.setEnabled(true);
    }//GEN-LAST:event_btnProximoActionPerformed

    private void consultar() {
        VeiculoDAO dao = new VeiculoDAO();
        ArrayList<Veiculo> veiculos = dao.listarVeiculosPaginacao(posicao, qtdPorPagina);
        veiculoTableModel.addListaNova(veiculos);
    }

    private void txtPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaginaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaginaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int indice = this.tabelaVeiculos.getSelectedRow();

        if (indice >= 0) {
            Veiculo objetoVeiculo = this.veiculoTableModel.getVeiculo(indice);
            FormListagemClienteLocacao janela = new FormListagemClienteLocacao(objetoVeiculo);
            janela.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um veículo");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void atualizar() {
        this.veiculoTableModel.atualizarTabela();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormListagemVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListagemVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListagemVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListagemVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormListagemVeiculos().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnProximo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVeiculos;
    private javax.swing.JTextField txtPagina;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtTotalPaginas;
    // End of variables declaration//GEN-END:variables
}
