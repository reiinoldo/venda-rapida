/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Maicon
 */
public class VendaVO {

    private int codigoVenda;
    private int idCliente;
    private String nomeCliente;
    private String loginUsuario;
    private Date dataVenda;
    private double desconto;
    private double valor;
    private List itens;

    public VendaVO(int codigoVenda, int idCliente, String nomeCliente, String loginUsuario, Date dataVenda, double desconto, double valor, List items) {
        this.codigoVenda = codigoVenda;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.loginUsuario = loginUsuario;
        this.dataVenda = dataVenda;
        this.desconto = desconto;
        this.valor = valor;
        this.itens = items;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public double getDesconto() {
        return desconto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public List getItens() {
        return itens;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getValor() {
        return valor;
    }
}
