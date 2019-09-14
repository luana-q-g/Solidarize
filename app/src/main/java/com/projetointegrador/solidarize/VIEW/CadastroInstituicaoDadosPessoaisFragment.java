package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroInstituicaoDadosPessoaisFragment extends Fragment {
    private EditText nome;
    private EditText email;
    private EditText cnpj;
    private EditText telefone;

    private Button btn_continuar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_instituicao_dados_pessoais, container, false);

        nome= view.findViewById(R.id.txt_nome);
        email= view.findViewById(R.id.txt_email);
        cnpj= view.findViewById(R.id.txt_cnpj);
        telefone= view.findViewById(R.id.txt_telefone);
        btn_continuar= view.findViewById(R.id.btn_continuar_dados_pessoais_instituicao);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String n, e, c, t, d;
                n= nome.getText().toString();
                e= email.getText().toString();
                c= cnpj.getText().toString();
                t= telefone.getText().toString();

                Instituicao inst= new Instituicao();
                inst.setNome(n);
                inst.setEmail(e);
                inst.setCnpj(c);

                TelefoneUsuario tel= new TelefoneUsuario(e, t);

                CadastroInstituicao act= (CadastroInstituicao) getActivity();
                act.setDadosPessoais(inst, tel);*/

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroInstituicaoEnderecoFragment cadastro_endereco= new CadastroInstituicaoEnderecoFragment();
                ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_endereco);
                ft.commit();
            }
        });

        return view;
    }
}
