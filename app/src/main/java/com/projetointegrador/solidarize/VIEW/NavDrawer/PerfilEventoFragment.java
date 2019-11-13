package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class PerfilEventoFragment extends Fragment {
    private TextView lbl_titulo_evento;
    private TextView lbl_data_i;
    private TextView lbl_data_f;
    private TextView lbl_hora_i;
    private TextView lbl_hora_f;
    private TextView lbl_descricao;
    private Button btn_nome_instituicao;
    private TextView lbl_id_instituicao;
    private TextView txt_endereco1;
    private TextView txt_endereco2;
    private TextView txt_email_contato;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_perfil_eventos, container, false);

        lbl_titulo_evento= view.findViewById(R.id.lbl_titulo_evento);
        lbl_data_i= view.findViewById(R.id.lbl_data_inicio);
        lbl_data_f= view.findViewById(R.id.lbl_data_final);
        lbl_hora_i= view.findViewById(R.id.lbl_hora_inicial);
        lbl_hora_f= view.findViewById(R.id.lbl_hora_final);
        lbl_descricao= view.findViewById(R.id.lbl_descricao);
        btn_nome_instituicao= view.findViewById(R.id.btn_nome_instituicao);
        lbl_id_instituicao= view.findViewById(R.id.txt_id_instituicao);
        txt_endereco1= view.findViewById(R.id.txt_endereco1);
        txt_endereco2= view.findViewById(R.id.txt_endereco2);
        txt_email_contato= view.findViewById(R.id.txt_email);

        //pega o id do evento da activity
        NavDrawMenu act= (NavDrawMenu) getActivity();

        //pega no nó específico com o id do evento
        DatabaseReference dados_evento= BD.child("evento").child(act.getIdEvento());
        dados_evento.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Evento evento= dataSnapshot.getValue(Evento.class);

                setContentView(evento);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void setContentView (Evento evento){
        lbl_titulo_evento.setText(evento.getNome());
        lbl_data_i.setText(evento.getDt_inicio());
        lbl_data_f.setText(evento.getDt_fim());
        lbl_hora_i.setText(evento.getHra_inicio());
        lbl_hora_f.setText(evento.getHra_fim());
        lbl_descricao.setText(evento.getDescricao());
        txt_endereco1.setText(evento.getEstado()+", "+evento.getCidade());
        txt_endereco2.setText(evento.getRua()+", "+evento.getNumero());
        txt_email_contato.setText(evento.getEmail_usuario());
    }
}
