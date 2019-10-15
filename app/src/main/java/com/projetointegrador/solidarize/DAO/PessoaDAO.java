package com.projetointegrador.solidarize.DAO;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Pessoa;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PessoaDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference pessoaDao= DB.child("pessoa");

    public PessoaDAO(){};

    public void inserirUsuarioPessoa(Pessoa pessoa){
        String id= pessoaDao.push().getKey();

        pessoa.setId(id);

        pessoaDao.child(id).setValue(pessoa);
    }

    public void alterarUsuarioPessoa (Pessoa pessoa){
        String id= pessoa.getId();

        pessoaDao.child(id).setValue(pessoa);
    }

    public DatabaseReference getUsuarioPessoaNo (String id){
        return pessoaDao.child(id);
    }

}

