package com.projetointegrador.solidarize.DAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Usuario;

public class UsuarioDAO {
    private DatabaseReference DB= FirebaseDatabase.getInstance().getReference();

    DatabaseReference usuarioDao= DB.child("usuario");

    public UsuarioDAO(){};

    /*public void inserirUsuario(Usuario usuario){
        DatabaseReference u= usuarioDao.child(); // rever como fazer isso

        u.child("email").setValue(usuario.getEmail());
        u.child("nome").setValue(usuario.getNome());
        u.child("senha").setValue(usuario.getSenha());
    }*/

}
