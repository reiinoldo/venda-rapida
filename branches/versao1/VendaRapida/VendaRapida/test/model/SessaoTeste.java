/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class SessaoTeste {
    
    private Sessao ss, s2;
    private Usuario user1, user2;
    
    public SessaoTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ss = Sessao.getInstance();
        s2 = Sessao.getInstance();
                
        user1 = new Usuario();
        user2 = new Usuario();
        
        user1.setLogin("Joe óculos");          
        user2.setLogin("Jow Jow");
        
        ss.setUsuario(user1);
        s2.setUsuario(user2);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     * Como usa-se uma única instancia
     * o ss deve ter o Login do ultimo usuário setado.
     */
    @Test
    public void testaSessaoUsuario1(){        
               
        assertEquals(ss.getUsuario().getLogin(), user2.getLogin());        
        
    }
    
    /**
     * Apenas compara.
     *
     */    
    @Test
    public void testaSessaoUsuario2(){        
                        
        assertEquals(s2.getUsuario().getLogin(), user2.getLogin());        
        
    }
    
    /**
     * Limpa a instancia 
     * e cria-se uma nova 
     */
    @Test
    public void testaSessaoUsuario1Mofificado(){
        ss = null;
        ss = Sessao.getInstance();
        ss.setUsuario(user1);
        
        assertEquals(ss.getUsuario().getLogin(), user1.getLogin());
    }
}