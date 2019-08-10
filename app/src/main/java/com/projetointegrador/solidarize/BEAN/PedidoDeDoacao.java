package com.projetointegrador.solidarize.BEAN;

import java.sql.Time;
import java.util.Date;

public class PedidoDeDoacao {
    private String cnpj_instituicao;
    private Date dt_cadastro;
    private Time hra_cadastro;
    private Date dt_validade;
    private int nivel_urgencia;
    private String item;

    public PedidoDeDoacao(String cnpj, Date dt_c, Time hra_c, Date dt_v, int nivel, String item){
        this.cnpj_instituicao= cnpj;
        this.dt_cadastro= dt_c;
        this.hra_cadastro= hra_c;
        this.dt_validade= dt_v;
        this.nivel_urgencia= nivel;
        this.item= item;
    }

    public String getCnpj_instituicao() {
        return cnpj_instituicao;
    }

    public void setCnpj_instituicao(String cnpj_instituicao) {
        this.cnpj_instituicao = cnpj_instituicao;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Time getHra_cadastro() {
        return hra_cadastro;
    }

    public void setHra_cadastro(Time hra_cadastro) {
        this.hra_cadastro = hra_cadastro;
    }

    public Date getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    public int getNivel_urgencia() {
        return nivel_urgencia;
    }

    public void setNivel_urgencia(int nivel_urgencia) {
        this.nivel_urgencia = nivel_urgencia;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
