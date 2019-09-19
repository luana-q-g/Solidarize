package com.projetointegrador.solidarize.BEAN;

import java.sql.Time;
import java.util.Date;

public class PedidoDeDoacao {
    private String id;
    private String cnpj_instituicao;
    private String item;
    private String meta_qtd;
    private String tipo_pedido;
    private String dt_validade;
    private String nivel_urgencia;
    private String estado;
    private String cidade;
    private String rua;
    private String complemento;

    public PedidoDeDoacao(){
    }

    public PedidoDeDoacao(String item, String meta_qtd, String tipo_pedido, String dt_validade, String nivel_urgencia, String estado, String cidade, String rua, String complemento) {
        this.item = item;
        this.meta_qtd = meta_qtd;
        this.tipo_pedido = tipo_pedido;
        this.dt_validade = dt_validade;
        this.nivel_urgencia = nivel_urgencia;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.complemento = complemento;
    }

    public PedidoDeDoacao(String cnpj_instituicao, String item, String meta_qtd, String tipo_pedido, String dt_validade, String nivel_urgencia, String estado, String cidade, String rua, String complemento) {
        this.cnpj_instituicao = cnpj_instituicao;
        this.item = item;
        this.meta_qtd = meta_qtd;
        this.tipo_pedido = tipo_pedido;
        this.dt_validade = dt_validade;
        this.nivel_urgencia = nivel_urgencia;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.complemento = complemento;
    }

    public String getCnpj_instituicao() {
        return cnpj_instituicao;
    }

    public void setCnpj_instituicao(String cnpj_instituicao) {
        this.cnpj_instituicao = cnpj_instituicao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    public String getMeta_qtd() {
        return meta_qtd;
    }

    public void setMeta_qtd(String meta_qtd) {
        this.meta_qtd = meta_qtd;
    }

    public String getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(String dt_validade) {
        this.dt_validade = dt_validade;
    }

    public String getNivel_urgencia() {
        return nivel_urgencia;
    }

    public void setNivel_urgencia(String nivel_urgencia) {
        this.nivel_urgencia = nivel_urgencia;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
