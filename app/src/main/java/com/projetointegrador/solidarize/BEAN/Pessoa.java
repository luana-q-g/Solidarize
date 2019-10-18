package com.projetointegrador.solidarize.BEAN;

public class Pessoa extends Usuario{
    final private String tipo_usuario= "pessoa";

    private String id;
    private String cpf;
    private String email_usuario;
    private String data_nasc;
    private String telefone;
    private String cidade;
    private String estado;

    public Pessoa(){
    }

    public Pessoa(String email, String nome, String cpf, String data_nasc, String tel, String cidade, String estado){
        super(email, nome);
        this.cpf= cpf;
        this.data_nasc= data_nasc;
        this.telefone= tel;
        this.cidade= cidade;
        this.estado= estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_usuario(){
        return this.tipo_usuario;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
