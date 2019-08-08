package com.projetointegrador.solidarize.BEAN;

public class Usuario {
    protected String email;
    protected String nome;
    protected String senha;

    public Usuario(String email, String nome, String senha){
        this.email= email;
        this.nome= nome;
        this.senha= senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
