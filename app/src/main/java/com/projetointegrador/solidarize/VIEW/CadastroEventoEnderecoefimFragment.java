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

public class CadastroEventoEnderecoefimFragment extends Fragment {

    private Spinner estado;
    private Spinner cidade;
    private EditText rua;
    private EditText complemento;
    private EditText descricao;

    private Button btn_voltar;
    private Button btn_cadastrar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_evento_enderecoefim, container, false);

        estado= view.findViewById(R.id.spin_estados);
        cidade= view.findViewById(R.id.spin_cidades);
        rua= view.findViewById(R.id.txt_rua);
        complemento= view.findViewById(R.id.txt_complemento);
        btn_voltar= view.findViewById(R.id.btn_voltar_enderecoefim_eventos);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_enderecoefim_eventos);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cadastro
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroEventoInfosFragment cadastro_infos= new CadastroEventoInfosFragment();
                ft.replace(R.id.place_holder_info_cadastro_evento, cadastro_infos);
                ft.commit();
            }
        });

        return view;
    }
}
