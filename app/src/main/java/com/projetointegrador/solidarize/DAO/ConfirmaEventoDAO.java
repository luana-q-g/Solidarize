package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.ConfirmaEvento;
import com.projetointegrador.solidarize.BEAN.SalvaEvento;

public class ConfirmaEventoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference confirmaEventoDao= DB.child("confirmaEvento");

    public ConfirmaEventoDAO(){}

    public void inserirEventoConfirmado(ConfirmaEvento confirmaEvento){
        String id= confirmaEvento.getIdUsuario();
        confirmaEventoDao.child(id).child(confirmaEvento.getIdEvento()).setValue(confirmaEvento);
    }

    public void excluirEventoConfirmado (String id_usuario, String id_evento){
        confirmaEventoDao.child(id_usuario).child(id_evento).setValue(null);
    }
}
