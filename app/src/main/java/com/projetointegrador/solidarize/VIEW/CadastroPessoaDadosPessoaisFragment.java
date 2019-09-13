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

import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.R;

public class CadastroPessoaDadosPessoaisFragment extends Fragment {
    private EditText nome;
    private EditText email;
    private EditText cpf;
    private EditText telefone;
    private EditText dt_nasc;

    private Button btn_continuar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pessoa_dados_pessoais, container, false);

        nome= view.findViewById(R.id.txt_nome);
        email= view.findViewById(R.id.txt_email);
        cpf= view.findViewById(R.id.txt_cpf);
        telefone= view.findViewById(R.id.txt_telefone);
        dt_nasc= view.findViewById(R.id.txt_dt_nasc);
        btn_continuar= view.findViewById(R.id.btn_continuar);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String n, e, c, t, d;
                n= nome.getText().toString();
                e= email.getText().toString();
                c= cpf.getText().toString();
                t= telefone.getText().toString();
                d= dt_nasc.getText().toString();

                Pessoa pessoa= new Pessoa();
                pessoa.setNome(n);
                pessoa.setEmail(e);
                pessoa.setCpf(c);
                pessoa.setData_nasc(d);

                TelefoneUsuario tel= new TelefoneUsuario(e, t);

                CadastroPessoa act= (CadastroPessoa) getActivity();
                act.setDadosPessoais(pessoa, tel);*/

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroPessoaEnderecoFragment cadastro_endereco= new CadastroPessoaEnderecoFragment();
                ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_endereco);
                ft.commit();
            }
        });

        return view;
    }
}
