package controller.impl;

public class RegraNegocioException extends Exception {
    private String mensagem;
    
    public RegraNegocioException(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String getMessage() {
        return mensagem;
    }
}