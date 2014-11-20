package model;

public class Sessao {
    private Usuario usuario;
    
    private static Sessao sessao;
    
    private Sessao() {}
    
    public static Sessao getInstance() {
        if (sessao == null)
            sessao = new Sessao();
        return sessao;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
