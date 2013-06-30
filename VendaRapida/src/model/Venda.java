package model;

import controller.impl.RegraNegocioException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {

    public enum TipoDesconto {

        PORCENTAGEM, DINHEIRO;
    }
    public static final String TABELA_VENDA = "vendarapida.venda";
    public static final String CAMPO_CODIGOVENDA = "venda.codigovenda";
    private int codigoVenda;
    public static final String CAMPO_IDCLIENTE = "venda.idcliente";
    private int idCliente;
    public static final String CAMPO_CODIGOPAGSEGURO = "venda.codigopagseguro";
    private String codigoPagSeguro;
    public static final String CAMPO_DATAVENDA = "venda.datavenda";
    private Date dataVenda;
    public static final String CAMPO_DESCONTO = "venda.desconto";
    private double desconto;
    public static final String CAMPO_LOGINUSUARIO = "venda.loginusuario";
    private String loginUsuario;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public Venda() {
        items = new ArrayList<Item>();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        item.setCodigoVenda(codigoVenda);
        this.items.add(item);
    }

    public void removerItem(Item item) {
        this.items.remove(item);
    }

    public void removerItem(int indexItem) {
        this.items.remove(indexItem);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public int getQuantidadeItens() {
        int qtd = 0;
        for (Item item : items) {
            qtd += item.getQuantidade();
        }
        return qtd;
    }

    public double getValorTotalComDesconto() {
        double valorTotalSemDesconto = 0;
        for (Item item : items) {
            valorTotalSemDesconto += item.getValor() * item.getQuantidade();
        }
        return valorTotalSemDesconto - desconto;
    }

    public double getValorTotalSemDesconto() {
        double valorTotalSemDesconto = 0;
        for (Item item : items) {
            valorTotalSemDesconto += item.getValor() * item.getQuantidade();
        }
        return valorTotalSemDesconto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoPagSeguro() {
        return codigoPagSeguro;
    }

    public void setCodigoPagSeguro(String codigoPagSeguro) {
        this.codigoPagSeguro = codigoPagSeguro;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setDesconto(TipoDesconto tipo, double desconto) throws RegraNegocioException {
        double valorTotal = getValorTotalSemDesconto();
        double descontoTotal = 0;
        switch (tipo) {
            case PORCENTAGEM:
                if (desconto <= 100) {
                    descontoTotal = (valorTotal * desconto) / 100;
                } else {
                    throw new RegraNegocioException("Você não pode dar um desconto maior que 100%.");
                }
                break;
            case DINHEIRO:
                if (desconto <= valorTotal) {
                    descontoTotal = desconto;
                } else {
                    throw new RegraNegocioException("Você não pode dar um desconto maior que o valor da compra.");
                }
                break;
        }
        this.desconto = descontoTotal;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }
}
