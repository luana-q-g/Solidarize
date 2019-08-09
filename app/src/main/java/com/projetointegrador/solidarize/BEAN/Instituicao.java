package com.projetointegrador.solidarize.BEAN;

public class Instituicao extends Usuario{
    private String cnpj;
    private String email_usuario;
    private String descricao;
    private String cidade;
    private String estado;
    private String rua;
    private String numero;

    public Instituicao(String email, String nome, String senha, String cnpj, String descricao, String cidade, String estado, String rua, String numero){
        super(email, nome, senha);
        this.cnpj= cnpj;
        this.email_usuario= email;
        this.descricao= descricao;
        this.cidade= cidade;
        this.estado= estado;
        this.rua= rua;
        this.numero= numero;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}