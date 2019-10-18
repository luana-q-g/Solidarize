package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class CadastroPessoaEnderecoFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private Spinner estado;
    private Spinner cidade;

    private Button btn_voltar;
    private Button btn_continuar;

    private String tipo;
    public CadastroPessoaEnderecoFragment(String tipo){
        this.tipo= tipo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pessoa_endereco, container, false);

        estado= view.findViewById(R.id.spin_estados);
        cidade= view.findViewById(R.id.spin_cidades);
        btn_voltar= view.findViewById(R.id.btn_voltar_endereco_pessoa);
        btn_continuar= view.findViewById(R.id.btn_continuar_endereco_pessoa);

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e;

                    //????
                    //c= cidade.getSelectedItem().toString();
                    //e= estado.getSelectedItem().toString();
                    c= "São Carlos";
                    e= "SP";

                    CadastroPessoa act= (CadastroPessoa) getActivity();
                    act.setEndereco(c, e);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaSenhaFragment cadastro_senha= new CadastroPessoaSenhaFragment();
                    ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_senha);
                    ft.commit();
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment(CadastroPessoaDadosPessoaisFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroPessoa act= (EdicaoCadastroPessoa) getActivity();

            btn_continuar.setText("Editar");

            //cidade.setText(act.getPessoa().getCidade());
            //estado.setText(act.getPessoa().getEstado());

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e;

                    //????
                    //c= cidade.getSelectedItem().toString();
                    //e= estado.getSelectedItem().toString();
                    c= "São Carlos";
                    e= "SP";

                    act.setEndereco(c, e);

                    PessoaDAO pessoaDao= new PessoaDAO();
                    pessoaDao.alterarUsuarioPessoa(act.getPessoa());

                    UsuarioLogado.getInstance().setUsuario(act.getPessoa());
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment(CadastroPessoaDadosPessoaisFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_pessoa, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }



        return view;
    }
}
