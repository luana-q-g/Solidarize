package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;

public class CadastroUsuarioEventoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference cadastroDao= DB.child("cadastroUsuarioEvento");

    public CadastroUsuarioEventoDAO(){}

    public void inserirCadastroUsuarioEvento(CadastroUsuarioEvento cadastro){
        String id= cadastro.getIdUsuario();
        cadastroDao.child(id).child(cadastro.getIdEvento()).setValue(cadastro);
    }

    public void alterarCadastroUsuarioEvento(CadastroUsuarioEvento cadastro){
        String id= cadastro.getIdUsuario();
        cadastroDao.child(id).child(cadastro.getIdEvento()).setValue(cadastro);
    }

    public void excluirCadastroUsuarioEvento (String id_usuario, String id_evento){
        cadastroDao.child(id_usuario).child(id_evento).setValue(null);
    }
}
