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

}
