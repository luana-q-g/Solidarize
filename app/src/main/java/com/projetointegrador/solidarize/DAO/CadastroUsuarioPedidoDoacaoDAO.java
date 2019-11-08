package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;

public class CadastroUsuarioPedidoDoacaoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference cadastroDao= DB.child("cadastroUsuarioPedido");

    public CadastroUsuarioPedidoDoacaoDAO(){}

    public void inserirCadastroUsuarioPedido(CadastroUsuarioPedidoDoacao cadastro){
        String id_usuario= cadastro.getIdUsuario();
        cadastroDao.child(id_usuario).child(cadastro.getIdPedido()).setValue(cadastro);
    }

    public void alterarCadastroUsuarioPedido(CadastroUsuarioPedidoDoacao cadastro){
        String id_usuario= cadastro.getIdUsuario();
        cadastroDao.child(id_usuario).child(cadastro.getIdPedido()).setValue(cadastro);
    }

    public void excluirCadastroUsuarioPedido (String id_usuario, String id_pedido){
        cadastroDao.child(id_usuario).child(id_pedido).setValue(null);
    }
}
