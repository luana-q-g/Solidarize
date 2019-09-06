package com.projetointegrador.solidarize.BEAN;

public class TelefoneUsuario {
    private String email_usuario;
    private String telefone;

    public TelefoneUsuario(){
    }

    public TelefoneUsuario(String email, String tel){
        this.email_usuario= email;
        this.telefone= tel;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
