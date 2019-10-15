package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EdicaoCadastroPessoa extends AppCompatActivity {
    private FrameLayout place_holder;

    private Pessoa pessoa= new Pessoa();

    private PessoaDAO pessoaDAO= new PessoaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_cadastro_pessoa);

        place_holder= findViewById(R.id.place_holder_info_edicao_cadastro_pessoa);

        /*String id= "-LqvLtlbg9ISOM4dr8Rk";

        DatabaseReference pessoa_dados= pessoaDAO.getUsuarioPessoaNo(id);
        //recuperacao de dados do firebase
        pessoa_dados.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ) {
                    Pessoa p1= dataSnapshot.getValue(Pessoa.class);

                    pessoa.setNome(p1.getNome());
                    pessoa.setId(p1.getId());
                    pessoa.setEmail(p1.getEmail());
                    pessoa.setSenha(p1.getSenha());
                    pessoa.setCpf(p1.getCpf());
                    pessoa.setData_nasc(p1.getData_nasc());
                    pessoa.setCidade(p1.getCidade());
                    pessoa.setEstado(p1.getEstado());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //abertura do fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment(CadastroPessoaDadosPessoaisFragment.EDICAO);
        ft.replace(R.id.place_holder_info_edicao_cadastro_pessoa, cadastro_dados_pessoais);
        ft.commit();


    }

    public void setDadosPessoais(String nome, String email, String cpf, String telefone, String dt_nasc){
        this.pessoa.setNome(nome);
        this.pessoa.setEmail(email);
        this.pessoa.setCpf(cpf);
        this.pessoa.setTelefone(telefone);
        this.pessoa.setData_nasc(dt_nasc);
    }

    public void setEndereco(String cidade, String estado){
        this.pessoa.setCidade(cidade);
        this.pessoa.setEstado(estado);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
