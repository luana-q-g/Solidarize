package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.DAO.InstituicaoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroInstituicaoSenhaFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroInstituicaoSenhaFragment(String tipo){
        this.tipo= tipo;
    }

    private EditText txt_descricao;
    private EditText txt_senha;
    private EditText txt_confirma_senha;
    private Button btn_voltar;
    private Button btn_cadastrar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_instituicao_senha, container, false);

        txt_descricao= view.findViewById(R.id.txt_descricao);
        txt_senha= view.findViewById(R.id.txt_senha);
        txt_confirma_senha= view.findViewById(R.id.txt_confirma_senha);
        btn_voltar= view.findViewById(R.id.btn_voltar_senha_instituicao);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_instituicao);

        if(tipo.contentEquals(CADASTRO)){
            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cadastrar
                    String senha, confirma_senha, descricao;

                    senha= txt_senha.getText().toString();
                    confirma_senha= txt_confirma_senha.getText().toString();
                    descricao= txt_descricao.getText().toString();

                    if(senha.equals(confirma_senha)){
                        CadastroInstituicao cadastro= (CadastroInstituicao) getActivity();
                        Instituicao instituicao= cadastro.getInstituicao();

                        instituicao.setSenha(senha);
                        instituicao.setDescricao(descricao);

                        InstituicaoDAO instituicaoDao= new InstituicaoDAO();
                        instituicaoDao.inserirUsuarioInstituicao(instituicao);

                        //inserir telefone
                    }
                    else{
                        Toast.makeText(getContext(), "Coloque a mesma senha em ambos os campos!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoEnderecoFragment cadastro_endereco= new CadastroInstituicaoEnderecoFragment(CadastroInstituicaoEnderecoFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_endereco);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            btn_cadastrar.setText("Editar");
            txt_senha.setEnabled(false);
            txt_confirma_senha.setEnabled(false);

            final EdicaoCadastroInstituicao act= (EdicaoCadastroInstituicao) getActivity();

            txt_descricao.setText(act.getInstituicao().getDescricao());

            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //editar
                    String descricao;

                    descricao= txt_descricao.getText().toString();

                    Instituicao instituicao= act.getInstituicao();

                    instituicao.setDescricao(descricao);

                    InstituicaoDAO instituicaoDao= new InstituicaoDAO();
                    instituicaoDao.alterarUsuarioInstituicao(instituicao);
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoEnderecoFragment cadastro_endereco= new CadastroInstituicaoEnderecoFragment(CadastroInstituicaoEnderecoFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_instituicao, cadastro_endereco);
                    ft.commit();
                }
            });
        }



        return view;
    }
}
