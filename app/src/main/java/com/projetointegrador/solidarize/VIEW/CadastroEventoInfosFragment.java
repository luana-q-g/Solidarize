package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.DAO.EventoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroEventoInfosFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroEventoInfosFragment(String tipo){
        this.tipo= tipo;
    }

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

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n, di, df, hi, hf, t;

                    n= txt_nome.getText().toString();
                    di= txt_dt_inicio.getText().toString();
                    df= txt_dt_final.getText().toString();
                    hi= txt_hra_inicio.getText().toString();
                    hf= txt_hra_final.getText().toString();
                    t= "";
                    //t= txt_tipo.getSelectedItem().toString();

                    CadastroEvento act= (CadastroEvento) getActivity();

                    act.setInfos(n, di, df, hi, hf, t);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroEventoEnderecoefimFragment cadastro_enderecoefim= new CadastroEventoEnderecoefimFragment(CadastroEventoEnderecoefimFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_evento, cadastro_enderecoefim);
                    ft.commit();
                }
            });
        }
        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroEvento act= (EdicaoCadastroEvento) getActivity();
            EventoDAO eventoDAO= new EventoDAO();

            //recupera id do evento clicado
            String id_evento= act.getEvento().getId();

            //recuperando nó do evento
            DatabaseReference evento_dados= eventoDAO.getEventoNo(id_evento);

            //recuperacao de dados do firebase
            evento_dados.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if ( dataSnapshot.exists() ) {
                        Evento evento= dataSnapshot.getValue(Evento.class);

                        //salva evento resgatado na activity
                        act.setEvento(evento);

                        String n, di, df, hi, hf, t;
                        n= evento.getNome();
                        di= evento.getDt_inicio();
                        df= evento.getDt_fim();
                        hi= evento.getHra_inicio();
                        hf= evento.getHra_fim();
                        t= "";

                        //act.setDadosPessoais(n, e, c, t, dt); nao precisa, é so alterar os dados nos campos
                        //act.setEnderecoEFim(evento.getId(), evento.getEmail_usuario(), evento.getEstado(), evento.getCidade(), evento.getRua(), evento.getNumero(), evento.getDescricao(), evento.getMax_participantes());

                        //seta campos de edicao
                        setDadosView(n, di, df, hi, hf, t);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n, di, df, hi, hf, t;

                    n= txt_nome.getText().toString();
                    di= txt_dt_inicio.getText().toString();
                    df= txt_dt_final.getText().toString();
                    hi= txt_hra_inicio.getText().toString();
                    hf= txt_hra_final.getText().toString();
                    t= "";
                    //t= txt_tipo.getSelectedItem().toString();

                    EdicaoCadastroEvento act= (EdicaoCadastroEvento) getActivity();

                    //altera infos de Evento da activity
                    act.setInfos(n, di, df, hi, hf, t);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroEventoEnderecoefimFragment cadastro_enderecoefim= new CadastroEventoEnderecoefimFragment(CadastroEventoEnderecoefimFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_evento, cadastro_enderecoefim);
                    ft.commit();
                }
            });
        }

        return view;
    }

    public void setDadosView(String n, String dti, String dtf, String hi, String hf, String ti) {
        txt_nome.setText(n);
        txt_dt_inicio.setText(dti);
        txt_dt_final.setText(dtf);
        txt_hra_inicio.setText(hi);
        txt_hra_final.setText(hf);
        //txt_tipo.setText(ti);
    }
}
