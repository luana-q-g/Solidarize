package com.projetointegrador.solidarize.BEAN;

import java.sql.Time;
import java.util.Date;

public class Evento {
    private String email_usuario;
    private String nome;
    private String descricao;
    private String tipo;
    private Date dt_inicio;
    private Date dt_fim;
    private Time hra_inicio;
    private Time hra_fim;
    private int max_participantes;
    private String cidade;
    private String estado;
    private String rua;
    private String numero;

    public Evento(){
    }

    public Evento(String email, String nome, String descricao, String tipo, Date dt_i, Date dt_f, Time hra_i, Time hra_f, int max_p, String cidade, String estado, String rua, String numero){
        this.email_usuario= email;
        this.nome= nome;
        this.descricao= descricao;
        this.tipo= tipo;
        this.dt_inicio= dt_i;
        this.dt_fim= dt_f;
        this.hra_inicio= hra_i;
        this.hra_fim= hra_f;
        this.max_participantes= max_p;
        this.cidade= cidade;
        this.estado= estado;
        this.rua= rua;
        this.numero= numero;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Time getHra_inicio() {
        return hra_inicio;
    }

    public void setHra_inicio(Time hra_inicio) {
        this.hra_inicio = hra_inicio;
    }

    public Time getHra_fim() {
        return hra_fim;
    }

    public void setHra_fim(Time hra_fim) {
        this.hra_fim = hra_fim;
    }

    public int getMax_participantes() {
        return max_participantes;
    }

    public void setMax_participantes(int max_participantes) {
        this.max_participantes = max_participantes;
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
