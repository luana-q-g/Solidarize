package com.projetointegrador.solidarize.BEAN;

public class ConfirmaEvento {
    private String email_usuario;
    private String nome_evento;
    private String email_usuario_evento;

    public ConfirmaEvento(String email_u, String nome_evento, String email_u_e){
        this.email_usuario= email_u;
        this.nome_evento= nome_evento;
        this.email_usuario_evento= email_u_e;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getEmail_usuario_evento() {
        return email_usuario_evento;
    }

    public void setEmail_usuario_evento(String email_usuario_evento) {
        this.email_usuario_evento = email_usuario_evento;
    }
}
