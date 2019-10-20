package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroInstituicao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilInstituicaoPerfilFragment extends Fragment {
    private TextView txt_nome, txt_email, txt_cnpj, txt_descricao, txt_tel, txt_estado, txt_cidade, txt_rua, txt_complemento;
    private Button btn_editar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_instituicao_perfil, container, false);

        txt_nome= view.findViewById(R.id.txt_nome_instituicao);
        txt_email= view.findViewById(R.id.txt_email);
        txt_cnpj= view.findViewById(R.id.txt_cnpj);
        txt_descricao= view.findViewById(R.id.txt_descricao_instituicao);
        txt_tel= view.findViewById(R.id.txt_telefone);
        txt_estado= view.findViewById(R.id.txt_estado);
        txt_cidade= view.findViewById(R.id.txt_cidade);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        btn_editar= view.findViewById(R.id.btn_editar);

        Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();

        //txt_nome.setText();

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre activity com info de pessoa para editar
                Intent i_inst= new Intent(getActivity().getApplicationContext(), EdicaoCadastroInstituicao.class);
                startActivity(i_inst);
            }
        });

        return view;
    }

}
