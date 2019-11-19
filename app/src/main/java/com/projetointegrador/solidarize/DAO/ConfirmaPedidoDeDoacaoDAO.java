package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.ConfirmaPedidoDeDoacao;
import com.projetointegrador.solidarize.BEAN.SalvaPedidoDeDoacao;

public class ConfirmaPedidoDeDoacaoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference confirmaPedidoDao= DB.child("confirmaPedidoDeDoacao");

    public ConfirmaPedidoDeDoacaoDAO(){}

    public void inserirPedidoConfirmado(ConfirmaPedidoDeDoacao confirmaPedido){
        String id= confirmaPedido.getIdUsuario();
        confirmaPedidoDao.child(id).child(confirmaPedido.getIdPedido()).setValue(confirmaPedido);
    }

    public void excluirPedidoConfirmado (String id_usuario, String id_pedido){
        confirmaPedidoDao.child(id_usuario).child(id_pedido).setValue(null);
    }
}
