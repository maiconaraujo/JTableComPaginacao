/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rentcar.model;

import java.util.Calendar;

/**
 *
 * @author sala304b
 */
public class Locacao {    
    private int codigo;
    private Calendar dataLocacao;
    private Calendar dataDevolucao;
    private int qtdDiasLocacao;
    private String observacao;
    private double valorlocacao;
    private Cliente cliente;
    private Veiculo veiculo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Calendar dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValorlocacao() {
        return valorlocacao;
    }

    public void setValorlocacao(double valorlocacao) {
        this.valorlocacao = valorlocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getQtdDiasLocacao() {
        return qtdDiasLocacao;
    }

    public void setQtdDiasLocacao(int qtdDiasLocacao) {
        this.qtdDiasLocacao = qtdDiasLocacao;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    

}
