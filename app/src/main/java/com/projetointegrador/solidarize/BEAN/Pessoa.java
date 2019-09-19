package com.projetointegrador.solidarize.BEAN;

public class Pessoa extends Usuario{
    private String id;
    private String cpf;
    private String email_usuario;
    private String data_nasc;
    private String cidade;
    private String estado;
    private String senha;

    public Pessoa(){
    }

    public Pessoa(String email, String nome, String senha, String cpf, String data_nasc, String cidade, String estado){
        super(email, nome, senha);
        this.cpf= cpf;
        this.data_nasc= data_nasc;
        this.cidade= cidade;
        this.estado= estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
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

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
