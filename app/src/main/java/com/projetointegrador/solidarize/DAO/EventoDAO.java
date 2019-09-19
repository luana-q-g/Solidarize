package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Evento;

public class EventoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference eventoDao= DB.child("evento");

    public EventoDAO(){};

    public void inserirEvento(Evento evento){
        String id= eventoDao.push().getKey();

        evento.setId(id);

        eventoDao.child(id).setValue(evento);
    }
}
