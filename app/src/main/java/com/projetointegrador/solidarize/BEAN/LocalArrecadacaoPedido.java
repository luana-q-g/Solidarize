package com.projetointegrador.solidarize.BEAN;

import java.sql.Time;
import java.util.Date;

public class LocalArrecadacaoPedido {
    private String cnpj_instituicao_pedido;
    private Date dt_cadastro_pedido;
    private Time hra_cadastro_pedido;
    private String nome_local;
    private String cidade;
    private String estado;
    private String rua;
    private String numero;

    public LocalArrecadacaoPedido(){
    }

    public LocalArrecadacaoPedido(String cnpj, Date dt_c, Time hra_c, String nome, String cidade, String estado, String rua, String numero){
        this.cnpj_instituicao_pedido= cnpj;
        this.dt_cadastro_pedido= dt_c;
        this.hra_cadastro_pedido= hra_c;
        this.nome_local= nome;
        this.cidade= cidade;
        this.estado= estado;
        this.rua= rua;
        this.numero= numero;
    }

    public String getCnpj_instituicao_pedido() {
        return cnpj_instituicao_pedido;
    }

    public void setCnpj_instituicao_pedido(String cnpj_instituicao_pedido) {
        this.cnpj_instituicao_pedido = cnpj_instituicao_pedido;
    }

    public Date getDt_cadastro_pedido() {
        return dt_cadastro_pedido;
    }

    public void setDt_cadastro_pedido(Date dt_cadastro_pedido) {
        this.dt_cadastro_pedido = dt_cadastro_pedido;
    }

    public Time getHra_cadastro_pedido() {
        return hra_cadastro_pedido;
    }

    public void setHra_cadastro_pedido(Time hra_cadastro_pedido) {
        this.hra_cadastro_pedido = hra_cadastro_pedido;
    }

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
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
