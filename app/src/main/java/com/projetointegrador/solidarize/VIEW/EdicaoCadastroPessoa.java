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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_cadastro_pessoa);

        place_holder= findViewById(R.id.place_holder_info_edicao_cadastro_pessoa);

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

    public void setId(String id){
        this.pessoa.setId(id);
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
