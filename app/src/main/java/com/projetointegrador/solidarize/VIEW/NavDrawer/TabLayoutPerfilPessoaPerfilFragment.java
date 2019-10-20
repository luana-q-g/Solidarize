package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroPessoa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilPessoaPerfilFragment extends Fragment {
    private TextView txt_nome, txt_email, txt_cpf, txt_dt_nasc, txt_tel, txt_estado, txt_cidade;

    private Button btn_editar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_pessoa_perfil, container, false);

        txt_nome= view.findViewById(R.id.txt_nome_pessoa);
        txt_email= view.findViewById(R.id.txt_email);
        txt_cpf= view.findViewById(R.id.txt_cpf);
        txt_dt_nasc= view.findViewById(R.id.txt_data_nasc);
        txt_tel= view.findViewById(R.id.txt_telefone);
        txt_estado= view.findViewById(R.id.txt_estado);
        txt_cidade= view.findViewById(R.id.txt_cidade);
        btn_editar= view.findViewById(R.id.btn_editar);

        Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
        //funcao seta dados nos campos do perfil
        setDadosView(usuario_pessoa.getNome(), usuario_pessoa.getEmail(), usuario_pessoa.getCpf(), usuario_pessoa.getTelefone(), usuario_pessoa.getData_nasc(), usuario_pessoa.getEstado(), usuario_pessoa.getCidade());

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre activity com info de pessoa para editar
                Intent i_pesso= new Intent(getActivity().getApplicationContext(), EdicaoCadastroPessoa.class);
                startActivity(i_pesso);
            }
        });

        return view;
    }

    public void setDadosView(String n, String e, String c, String t, String dt, String es, String ci) {
        txt_nome.setText(n);
        txt_email.setText(e);
        txt_cpf.setText(c);
        txt_tel.setText(t);
        txt_dt_nasc.setText(dt);
        txt_cidade.setText(ci);
        txt_estado.setText(es);
    }
}
