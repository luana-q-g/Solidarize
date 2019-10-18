package com.projetointegrador.solidarize.BEAN;

public class UsuarioLogado {
    private Usuario usuario;

    private static final UsuarioLogado ourInstance = new UsuarioLogado();

    public static UsuarioLogado getInstance() {
        return ourInstance;
    }

    private UsuarioLogado() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void unsetUsuario() {
        this.usuario = null;
    }
}
