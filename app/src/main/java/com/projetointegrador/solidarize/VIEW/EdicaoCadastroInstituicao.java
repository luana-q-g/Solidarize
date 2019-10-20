package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EdicaoCadastroInstituicao extends AppCompatActivity {
    private FrameLayout place_holder;

    private Instituicao instituicao= new Instituicao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_cadastro_instituicao);

        place_holder= findViewById(R.id.place_holder_info_edicao_cadastro_instituicao);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroInstituicaoDadosPessoaisFragment cadastro_dados_pessoais= new CadastroInstituicaoDadosPessoaisFragment(CadastroInstituicaoDadosPessoaisFragment.EDICAO);
        ft.replace(R.id.place_holder_info_edicao_cadastro_instituicao, cadastro_dados_pessoais);
        ft.commit();
    }

    public void setDadosPessoais(String nome, String email, String cnpj, String telefone){
        this.instituicao.setNome(nome);
        this.instituicao.setEmail(email);
        this.instituicao.setCnpj(cnpj);
        this.instituicao.setTelefone(telefone);
    }

    public void setId(String id){
        this.instituicao.setId(id);
    }

    public void setDescricao(String descricao){
        this.instituicao.setDescricao(descricao);
    }

    public void setEndereco(String cidade, String estado, String rua, String complemento){
        this.instituicao.setEstado(estado);
        this.instituicao.setCidade(cidade);
        this.instituicao.setRua(rua);
        this.instituicao.setNumero(complemento);
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

}
