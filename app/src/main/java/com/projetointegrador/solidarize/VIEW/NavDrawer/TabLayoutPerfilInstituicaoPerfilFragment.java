package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroInstituicao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilInstituicaoPerfilFragment extends Fragment {
    private Button btn_editar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_instituicao_perfil, container, false);

        btn_editar= view.findViewById(R.id.btn_editar);

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
