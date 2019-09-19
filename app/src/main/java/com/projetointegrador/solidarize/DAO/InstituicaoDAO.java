package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Instituicao;

public class InstituicaoDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference instituicaoDao= DB.child("instituicao");

    public InstituicaoDAO(){};

    public void inserirUsuarioInstituicao(Instituicao instituicao){
        String id= instituicaoDao.push().getKey();

        instituicao.setId(id);

        instituicaoDao.child(id).setValue(instituicao);
    }
}
