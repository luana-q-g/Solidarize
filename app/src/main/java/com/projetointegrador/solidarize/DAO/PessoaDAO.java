package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Pessoa;

public class PessoaDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference pessoaDao= DB.child("usuario");

    public PessoaDAO(){};

    public void inserirUsuarioPessoa(Pessoa pessoa){
        String id= pessoaDao.push().getKey();

        pessoa.setId(id);

        pessoaDao.child(id).setValue(pessoa);
    }
}
