package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroEventoInfosFragment extends Fragment {
    private EditText txt_nome;
    private EditText txt_dt_inicio;
    private EditText txt_dt_final;
    private EditText txt_hra_inicio;
    private EditText txt_hra_final;
    private Spinner txt_tipo;

    private Button btn_continuar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_evento_infos, container, false);

        txt_nome= view.findViewById(R.id.txt_nome_evento);
        txt_dt_inicio= view.findViewById(R.id.txt_dt_inicio_evento);
        txt_dt_final= view.findViewById(R.id.txt_dt_fim_evento);
        txt_hra_inicio= view.findViewById(R.id.txt_hra_inicio_evento);
        txt_hra_final= view.findViewById(R.id.txt_hra_fim_evento);
        txt_tipo= view.findViewById(R.id.txt_tipo_evento);
        btn_continuar= view.findViewById(R.id.btn_continuar_infos_eventos);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n, di, df, hi, hf, t;

                n= txt_nome.getText().toString();
                di= txt_dt_inicio.getText().toString();
                df= txt_dt_final.getText().toString();
                hi= txt_hra_inicio.getText().toString();
                hf= txt_dt_final.getText().toString();
                t= "";
                //t= txt_tipo.getSelectedItem().toString();

                CadastroEvento act= (CadastroEvento) getActivity();

                act.setInfos(n, di, df, hi, hf, t);

                FragmentManager fm= act.getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroEventoEnderecoefimFragment cadastro_enderecoefim= new CadastroEventoEnderecoefimFragment();
                ft.replace(R.id.place_holder_info_cadastro_evento, cadastro_enderecoefim);
                ft.commit();
            }
        });

        return view;
    }
}
