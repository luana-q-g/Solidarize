package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;

public class PedidosDeDoacaoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference pedidoDao= DB.child("pedidoDeDoacao");

    public PedidosDeDoacaoDAO(){};

    public void inserirPedidoDeDoacao(PedidoDeDoacao pedido){
        String id= pedidoDao.push().getKey();

        pedido.setId(id);

        pedidoDao.child(id).setValue(pedido);
    }

    public void alterarPedidoDoacao(PedidoDeDoacao pedido){
        String id= pedido.getId();
        pedidoDao.child(id).setValue(pedido);
    }

    public void excluirPedidoDoacao (String id){
        pedidoDao.child(id).setValue(null);
    }

    public DatabaseReference getPedidoDoacaoNo (String id){
        return pedidoDao.child(id);
    }
}
