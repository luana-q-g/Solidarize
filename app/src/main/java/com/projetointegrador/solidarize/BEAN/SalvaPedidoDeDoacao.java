package com.projetointegrador.solidarize.BEAN;

import java.sql.Time;
import java.util.Date;

public class SalvaPedidoDeDoacao {
    private String email_usuario;
    private String cnpj_pedido;
    private Date dt_pedido;
    private Time hra_pedido;

    public SalvaPedidoDeDoacao(){
    }

    public SalvaPedidoDeDoacao(String email_u, String cnpj, Date dt, Time hra){
        this.email_usuario= email_u;
        this.cnpj_pedido= cnpj;
        this.dt_pedido= dt;
        this.hra_pedido= hra;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getCnpj_pedido() {
        return cnpj_pedido;
    }

    public void setCnpj_pedido(String cnpj_pedido) {
        this.cnpj_pedido = cnpj_pedido;
    }

    public Date getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public Time getHra_pedido() {
        return hra_pedido;
    }

    public void setHra_pedido(Time hra_pedido) {
        this.hra_pedido = hra_pedido;
    }
}
