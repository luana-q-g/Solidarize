package com.projetointegrador.solidarize.VIEW;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.DAO.EventoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroEventoEnderecoefimFragment extends Fragment {

    private Spinner txt_estado;
    private Spinner txt_cidade;
    private EditText txt_rua;
    private EditText txt_complemento;
    private EditText txt_descricao;
    private EditText txt_max_participantes;

    private Button btn_voltar;
    private Button btn_cadastrar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_evento_enderecoefim, container, false);

        txt_estado= view.findViewById(R.id.spin_estados);
        txt_cidade= view.findViewById(R.id.spin_cidades);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        txt_descricao= view.findViewById(R.id.txt_descricao_evento);
        txt_max_participantes= view.findViewById(R.id.txt_max_participantes);
        btn_voltar= view.findViewById(R.id.btn_voltar_enderecoefim_eventos);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_enderecoefim_eventos);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth_usuario.getCurrentUser() != null){
                    String e, c, r, com, d, m, email_usuario;
                    //e= txt_estado.getSelectedItem().toString();
                    //c= txt_cidade.getSelectedItem().toString();
                    e= "";
                    c= "";
                    r= txt_rua.getText().toString();
                    com= txt_complemento.getText().toString();
                    d= txt_descricao.getText().toString();
                    m= txt_max_participantes.getText().toString();

                    email_usuario= auth_usuario.getCurrentUser().getEmail();

                    //cadastro
                    CadastroEvento cadastro= (CadastroEvento) getActivity();
                    Evento evento= cadastro.getEvento();

                    evento.setEstado(e);
                    evento.setCidade(c);
                    evento.setRua(r);
                    evento.setNumero(com);
                    evento.setDescricao(d);
                    evento.setMax_participantes(m);
                    //email do usuario que cadastrou
                    evento.setEmail_usuario(email_usuario);

                    EventoDAO eventoDao= new EventoDAO();
                    eventoDao.inserirEvento(evento);

                    Toast.makeText(getContext(), "Evento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
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
