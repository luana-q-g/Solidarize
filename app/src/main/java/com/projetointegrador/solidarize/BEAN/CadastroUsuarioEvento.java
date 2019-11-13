package com.projetointegrador.solidarize.BEAN;

public class CadastroUsuarioEvento {
    private String id_usuario;
    private String id_evento;
    private String nome_evento;
    private String nome_instituicao;
    private String tipo_usuario;

    public CadastroUsuarioEvento(){}

    public CadastroUsuarioEvento(String id_u, String id_e, String nome_e, String nome_i, String tipo_u){
        this.id_usuario= id_u;
        this.id_evento= id_e;
        this.nome_evento= nome_e;
        this.tipo_usuario= tipo_u;
        if(tipo_u.contentEquals("pessoa")){
            this.nome_instituicao= null;
        }
        else{
            this.nome_instituicao= nome_i;
        }
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

    public String getNomeInstituicao() {
        return nome_instituicao;
    }

    public void setNomeInstituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public String getTipoUsuario() {
        return tipo_usuario;
    }

    public void setTipoUsuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
