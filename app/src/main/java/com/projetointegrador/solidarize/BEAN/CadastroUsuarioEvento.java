package com.projetointegrador.solidarize.BEAN;

public class CadastroUsuarioEvento {
    private String id_usuario;
    private String id_evento;
    private String nome_evento;

    public CadastroUsuarioEvento(){}

    public CadastroUsuarioEvento(String id_u, String id_e, String nome_e){
        this.id_usuario= id_u;
        this.id_evento= id_e;
        this.nome_evento= nome_e;
    }

    public String getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getIdEvento() {
        return id_evento;
    }

    public void setIdEvento(String id_evento) {
        this.id_evento = id_evento;
    }

    public String getNomeEvento() {
        return nome_evento;
    }

    public void setNomeEvento(String nome_evento) {
        this.nome_evento = nome_evento;
    }
}
