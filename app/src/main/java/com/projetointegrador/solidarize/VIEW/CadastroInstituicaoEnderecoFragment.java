package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroInstituicaoEnderecoFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroInstituicaoEnderecoFragment(String tipo){
        this.tipo= tipo;
    }

    private Spinner estado;
    private Spinner cidade;
    private EditText rua;
    private EditText complemento;

    private Button btn_voltar;
    private Button btn_continuar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_instituicao_endereco, container, false);

        estado= view.findViewById(R.id.spin_estados);
        cidade= view.findViewById(R.id.spin_cidades);
        rua= view.findViewById(R.id.txt_rua);
        complemento= view.findViewById(R.id.txt_complemento);
        btn_voltar= view.findViewById(R.id.btn_voltar_endereco_instituicao);
        btn_continuar= view.findViewById(R.id.btn_continuar_endereco_instituicao);

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e, r, com;

                    //????
                    //c= cidade.getSelectedItem().toString();
                    c= "São Carlos";
                    e= "SP";
                    //e= estado.getSelectedItem().toString();
                    r= rua.getText().toString();
                    com= complemento.getText().toString();

                    CadastroInstituicao act= (CadastroInstituicao) getActivity();
                    act.setEndereco(c, e, r, com);

                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoSenhaFragment cadastro_senha= new CadastroInstituicaoSenhaFragment(CadastroInstituicaoSenhaFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_senha);
                    ft.commit();
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoDadosPessoaisFragment cadastro_dados_pessoais= new CadastroInstituicaoDadosPessoaisFragment(CadastroInstituicaoDadosPessoaisFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroInstituicao act= (EdicaoCadastroInstituicao) getActivity();

            //cidade.setText(act.getPessoa().getCidade());
            //estado.setText(act.getPessoa().getEstado());
            rua.setText(act.getInstituicao().getRua());
            complemento.setText(act.getInstituicao().getNumero());

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e, r, com;

                    //????
                    //c= cidade.getSelectedItem().toString();
                    c= "São Carlos";
                    e= "SP";
                    //e= estado.getSelectedItem().toString();
                    r= rua.getText().toString();
                    com= complemento.getText().toString();

                    EdicaoCadastroInstituicao act= (EdicaoCadastroInstituicao) getActivity();
                    act.setEndereco(c, e, r, com);

                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoSenhaFragment cadastro_senha= new CadastroInstituicaoSenhaFragment(CadastroInstituicaoSenhaFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_instituicao, cadastro_senha);
                    ft.commit();
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoDadosPessoaisFragment cadastro_dados_pessoais= new CadastroInstituicaoDadosPessoaisFragment(CadastroInstituicaoDadosPessoaisFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_instituicao, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }



        return view;
    }
}
