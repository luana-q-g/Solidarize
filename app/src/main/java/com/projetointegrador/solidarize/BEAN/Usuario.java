package com.projetointegrador.solidarize.BEAN;

public abstract class Usuario {
    protected String email;
    protected String nome;

    public Usuario(){
    }

    public Usuario(String email, String nome){
        this.email= email;
        this.nome= nome;
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

    //isso possibilita saber que tipo é o usuario
    //sabendo o tipo, da para instanciar um objeto e dar cast do usuario para o tipo especifico (e então recuperar dados do objeto)
    public abstract String getTipo_usuario();
    //todos os filhos devem ter esse método (por isso é abstrato)
}
