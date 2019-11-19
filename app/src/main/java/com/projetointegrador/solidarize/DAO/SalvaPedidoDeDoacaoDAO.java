package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.SalvaEvento;
import com.projetointegrador.solidarize.BEAN.SalvaPedidoDeDoacao;

public class SalvaPedidoDeDoacaoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference salvaPedidoDao= DB.child("salvaPedidoDeDoacao");

    public SalvaPedidoDeDoacaoDAO(){}

    public void inserirPedidoSalvo(SalvaPedidoDeDoacao salvaPedido){
        String id= salvaPedido.getIdUsuario();
        salvaPedidoDao.child(id).child(salvaPedido.getIdPedido()).setValue(salvaPedido);
    }

    public void excluirPedidoSalvo (String id_usuario, String id_pedido){
        salvaPedidoDao.child(id_usuario).child(id_pedido).setValue(null);
    }
}
