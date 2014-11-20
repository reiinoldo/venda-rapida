/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author Maicon
 */
public class ItemVO {

    private String referenciaProduto;
    private int quantidade;
    private double valor;
    private String descricaoProduto;

    public ItemVO(String referenciaProduto, int quantidade, double valor, String descricaoProduto) {
        this.referenciaProduto = referenciaProduto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricaoProduto = descricaoProduto;
    }

    public String getReferenciaProduto() {
        return referenciaProduto;
    }

    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
}
