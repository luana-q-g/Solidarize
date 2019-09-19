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

    private Instituicao instituicao= new Instituicao();
    private TelefoneUsuario tel= new TelefoneUsuario();

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

    public void setDadosPessoais(String nome, String email, String cnpj, String telefone){
        this.instituicao.setNome(nome);
        this.instituicao.setEmail(email);
        this.instituicao.setCnpj(cnpj);
        this.tel.setTelefone(telefone);
        this.tel.setEmail_usuario(email);
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

    public TelefoneUsuario getTel() {
        return tel;
    }

    public void setTel(TelefoneUsuario tel) {
        this.tel = tel;
    }
}
