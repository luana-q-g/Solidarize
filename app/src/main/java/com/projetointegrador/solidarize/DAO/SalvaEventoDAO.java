package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.SalvaEvento;

public class SalvaEventoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference salvaEventoDao= DB.child("salvaEvento");

    public SalvaEventoDAO(){}

    public void inserirEventoSalvo(SalvaEvento salvaEvento){
        String id= salvaEvento.getIdUsuario();
        salvaEventoDao.child(id).child(salvaEvento.getIdEvento()).setValue(salvaEvento);
    }

    public void excluirEventoSalvo (String id_usuario, String id_evento){
        salvaEventoDao.child(id_usuario).child(id_evento).setValue(null);
    }
}
