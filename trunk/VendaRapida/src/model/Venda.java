package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {

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
        this.items.add(item);
    }

    public void removerItem(Item item) {
        this.items.remove(item);
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

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public double getValor() {
        double valor = 0;
        for (int i = 0; i < getItems().size(); i++) {
            valor += getItems().get(i).getValor();
        }
        return valor;
    }
}
