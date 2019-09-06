package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Pessoa;

public class PessoaDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference pessoaDao= DB.child("usuario");

    public PessoaDAO(){};

    public void inserirUsuarioPessoa(Pessoa pessoa){
        DatabaseReference p= pessoaDao.child(pessoa.getCpf()); // rever como fazer isso

        p.child("email").setValue(pessoa.getEmail());
        p.child("nome").setValue(pessoa.getNome());
        p.child("cpf").setValue(pessoa.getSenha());
        p.child("dt_nasc").setValue(pessoa.getData_nasc());
        p.child("cidade").setValue(pessoa.getCidade());
        p.child("estado").setValue(pessoa.getEstado());
    }
}
