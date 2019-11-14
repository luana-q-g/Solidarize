package com.projetointegrador.solidarize.BEAN;

public class CadastroUsuarioPedidoDoacao {
    private String id_usuario;
    private String id_pedido;
    private String nome_pedido;
    private String nome_instituicao;

    public CadastroUsuarioPedidoDoacao(){}

    public CadastroUsuarioPedidoDoacao(String id_u, String id_p, String nome_p, String nome_i){
        this.id_usuario= id_u;
        this.id_pedido= id_p;
        this.nome_pedido= nome_p;
        this.nome_instituicao= nome_i;
    }

    public String getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getIdPedido() {
        return id_pedido;
    }

    public void setIdPedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNomePedido() {
        return nome_pedido;
    }

    public void setNomePedido(String nome_pedido) {
        this.nome_pedido = nome_pedido;
    }

    public String getNomeInstituicao() {
        return nome_instituicao;
    }

    public void setNomeInstituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }
}
