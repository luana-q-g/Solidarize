package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroInstituicaoSenhaFragment extends Fragment {

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

        btn_voltar= view.findViewById(R.id.btn_voltar_senha_instituicao);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_instituicao);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cadastrar
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
