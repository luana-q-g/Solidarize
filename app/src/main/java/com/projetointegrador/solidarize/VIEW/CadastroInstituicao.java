package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.R;

public class CadastroInstituicao extends AppCompatActivity {
    private FrameLayout place_holder;

    private String nome;
    private String email;
    private String cnpj;
    private String telefone;
    private String cidade;
    private String estado;
    private String rua;
    private String complemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_instituicao);

        place_holder= findViewById(R.id.place_holder_info_cadastro_instituicao);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroInstituicaoDadosPessoaisFragment cadastro_dados_pessoais= new CadastroInstituicaoDadosPessoaisFragment();
        ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_dados_pessoais);
        ft.commit();
    }

    public void setDadosPessoais(Instituicao inst, TelefoneUsuario tel){
        this.nome= inst.getNome();
        this.email= inst.getEmail();
        this.cnpj= inst.getCnpj();
        this.telefone= tel.getTelefone();
    }

    public void setEndereco(String c, String e, String r, String com){
        this.estado= e;
        this.cidade= c;
        this.rua= r;
        this.complemento= com;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }
}
