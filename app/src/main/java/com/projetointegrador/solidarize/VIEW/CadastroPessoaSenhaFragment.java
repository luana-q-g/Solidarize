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
import android.widget.Toast;

import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class CadastroPessoaSenhaFragment extends Fragment {
    public static final String CADASTRO= "cadastro";

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
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pessoa_senha, container, false);

        txt_senha= view.findViewById(R.id.txt_senha);
        txt_confirma_senha= view.findViewById(R.id.txt_confirma_senha);
        btn_voltar= view.findViewById(R.id.btn_voltar_senha_pessoa);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_pessoa);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cadastrar
                String senha, confirma_senha;
                senha= txt_senha.getText().toString();
                confirma_senha= txt_confirma_senha.getText().toString();

                if(senha.equals(confirma_senha)){
                    CadastroPessoa cadastro= (CadastroPessoa) getActivity();
                    Pessoa pessoa= cadastro.getPessoa();
                    pessoa.setSenha(senha);

                    PessoaDAO pessoaDao= new PessoaDAO();
                    pessoaDao.inserirUsuarioPessoa(pessoa);

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

                CadastroPessoaEnderecoFragment cadastro_endereco= new CadastroPessoaEnderecoFragment(CadastroPessoaSenhaFragment.CADASTRO);
                ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_endereco);
                ft.commit();
            }
        });

        return view;
    }
}
