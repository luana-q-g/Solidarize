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

    private Pessoa pessoa= new Pessoa();
    private TelefoneUsuario tel= new TelefoneUsuario();

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

    public void setDadosPessoais(String nome, String email, String cpf, String telefone, String dt_nasc){
        this.pessoa.setNome(nome);
        this.pessoa.setEmail(email);
        this.pessoa.setCpf(cpf);
        this.tel.setTelefone(telefone);
        this.tel.setEmail_usuario(email);
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

    public TelefoneUsuario getTel() {
        return tel;
    }

    public void setTel(TelefoneUsuario tel) {
        this.tel = tel;
    }
}
