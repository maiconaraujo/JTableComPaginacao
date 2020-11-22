/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rentcar.model;

import br.com.rentcar.model.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sala305b
 */
public class ClienteTableModel extends AbstractTableModel {    

    private ArrayList<Cliente> clientes;
    
    public ClienteTableModel(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getCpf();
            case 1: return cliente.getNome();
            case 2: return cliente.getEndereco();
            case 3: return cliente.getSexo();
            case 4: return cliente.getTelefone();
            case 5: return cliente.getCelular();
            default: return "";
        }
        
    }   
 
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "CPF";
            case 1: return "Nome";
            case 2: return "Endereço";
            case 3: return "Sexo";
            case 4: return "Telefone";
            case 5: return "Celular";
            default: return "";
        }
    }

    public Cliente getCliente(int i){
        return this.clientes.get(i);
    }
    
    public void atualizaTabela(){
        fireTableDataChanged();
    }
    
}
