package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.R;

public class CadastroPessoa extends AppCompatActivity {
    private FrameLayout place_holder;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String dt_nasc;
    private String cidade;
    private String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        place_holder= findViewById(R.id.place_holder_info_cadastro_pessoa);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment();
        ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_dados_pessoais);
        ft.commit();
    }

    public void setDadosPessoais(Pessoa pessoa, TelefoneUsuario tel){
        this.nome= pessoa.getNome();
        this.email= pessoa.getEmail();
        this.cpf= pessoa.getCpf();
        this.telefone= tel.getTelefone();
        this.dt_nasc= pessoa.getData_nasc();
    }

    public void setEndereco(String c, String e){
        this.estado= e;
        this.cidade= c;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
