/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.impl.RegraNegocioException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reinoldo
 */
public class VendaTeste {

    private Venda venda;
    private Produto produto;
    private Item item;
    
    public VendaTeste() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        venda = new Venda();

        produto = new Produto();
        produto.setReferencia("998877");
        produto.setDescricao("Produto de Teste1");
        produto.setValor(2.75);
                       
        item = new Item(produto, 5);
        item.setCodigoVenda(1);
        venda.addItem(item);
        
        produto.setReferencia("998876");
        produto.setDescricao("Produto de Teste2");
        produto.setValor(75.13);
        
        item = new Item(produto, 3);
        item.setCodigoVenda(1);
        venda.addItem(item);
        
        produto.setReferencia("998875");
        produto.setDescricao("Produto de Teste3");
        produto.setValor(5);
        
        item = new Item(produto, 1);
        item.setCodigoVenda(1);
        venda.addItem(item);
        
        produto.setReferencia("998877");
        produto.setDescricao("Produto de Teste1");
        produto.setValor(2.75);
        
        item = new Item(produto, 2);
        item.setCodigoVenda(1);
        venda.addItem(item);

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testaQtdItens() {        
        
        assertEquals(11, venda.getQuantidadeItens());


    }
    
    @Test
    public void testaItems() {        
        
        assertEquals(item.getReferenciaProduto(), venda.getItem(0).getReferenciaProduto());

    }
    
    @Test
    public void testaDescontoTotalVendaPorcentagem() {        
        try {
            venda.setDesconto(Venda.TipoDesconto.PORCENTAGEM, 3.0);
        } catch (RegraNegocioException ex) {
            fail("Erro em Desconto!");
        }
        
        assertEquals(242.15, venda.getValorTotalComDesconto(), 0.001);

    }
    
    @Test
    public void testaDescontoTotalVendaDinheiro() {        
        try {
            venda.setDesconto(Venda.TipoDesconto.DINHEIRO, 3.0);
        } catch (RegraNegocioException ex) {
            fail("Erro em Desconto!");
        }
        
        assertEquals(246.64, venda.getValorTotalComDesconto(), 0.001);

    }
    
    @Test
    public void testaValorTotalVendaSemDesconto() {        
        venda.setDesconto(1.99);
        assertEquals(249.64, venda.getValorTotalSemDesconto(), 0.0001);

    }
    
    @Test
    public void testaValorTotalVendaMenosDesconto() {        
        venda.setDesconto(1.99);
        assertEquals(247.65, venda.getValor(), 0.0001);

    }
    
    
    @Test
    public void testaRemoveItem() {        
        venda.removerItem(1);
        assertEquals(8, venda.getQuantidadeItens());

    }
    
}