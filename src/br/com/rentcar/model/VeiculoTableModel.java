/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rentcar.model;

import br.com.rentcar.util.Utilidades;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sala305b
 */
public class VeiculoTableModel extends AbstractTableModel {    

    private ArrayList<Veiculo> veiculos;
    
    public VeiculoTableModel(ArrayList<Veiculo> listaDeVeiculos) {
        this.veiculos = listaDeVeiculos;
    }

    @Override
    public int getRowCount() {
        return this.veiculos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo objetoVeiculo = this.veiculos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return objetoVeiculo.getChassi();
            case 1:
                return objetoVeiculo.getPlaca();
            case 2:
                return objetoVeiculo.getMarca();
            case 3:
                return objetoVeiculo.getModelo();
            case 4:
                return objetoVeiculo.getAnoFabricacao();
            case 5:
                return Utilidades.formataValor(objetoVeiculo.getValorLocacao());
            case 6:
                return objetoVeiculo.getSituacao();
            default:
                return null;
        }
    }   

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "CHASSI";
            case 1:
                return "PLACA";
            case 2:
                return "MARCA";
            case 3:
                return "MODELO";
            case 4:
                return "ANO FABRICAÇÃO";
            case 5:
                return "VALOR LOCAÇÃO";
            case 6:
                return "SITUAÇÃO";
            default:
                return "";
        }
    }
    
    public Veiculo getVeiculo(int indice){
        return this.veiculos.get(indice);
    }
    
    public void removeVeiculo(int indice){
        this.veiculos.remove(indice);
        
        fireTableDataChanged();
    }
    
    public void atualizarTabela(){
        fireTableDataChanged();
    }
    
    public void addListaNova(ArrayList<Veiculo> listaNova){
        this.veiculos.clear();
        
        this.veiculos.addAll(listaNova);
        fireTableDataChanged();
        
    }
    
}
