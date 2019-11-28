package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.ConfirmaEvento;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.SalvaEvento;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.ConfirmaEventoDAO;
import com.projetointegrador.solidarize.DAO.SalvaEventoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PerfilEventoFragment extends Fragment {
    private TextView lbl_titulo_evento;
    private ImageButton btn_salvar_evento;
    private ImageButton btn_confirmar_evento;
    private TextView lbl_data_i;
    private TextView lbl_data_f;
    private TextView lbl_hora_i;
    private TextView lbl_hora_f;
    private TextView lbl_descricao;
    private TextView lbl_organizado_por;
    private Button btn_nome_instituicao;
    private TextView lbl_id_instituicao;
    private TextView txt_endereco1;
    private TextView txt_endereco2;
    private TextView txt_email_contato;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    private String id_usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_perfil_eventos, container, false);

        lbl_titulo_evento= view.findViewById(R.id.lbl_titulo_evento);
        btn_salvar_evento= view.findViewById(R.id.btn_salvar_evento);
        btn_confirmar_evento= view.findViewById(R.id.btn_confirma_presenca);
        lbl_data_i= view.findViewById(R.id.lbl_data_inicio);
        lbl_data_f= view.findViewById(R.id.lbl_data_final);
        lbl_hora_i= view.findViewById(R.id.lbl_hora_inicial);
        lbl_hora_f= view.findViewById(R.id.lbl_hora_final);
        lbl_descricao= view.findViewById(R.id.lbl_descricao);
        lbl_organizado_por= view.findViewById(R.id.lbl_organizacao);
        btn_nome_instituicao= view.findViewById(R.id.btn_nome_instituicao);
        lbl_id_instituicao= view.findViewById(R.id.txt_id_instituicao);
        txt_endereco1= view.findViewById(R.id.txt_endereco1);
        txt_endereco2= view.findViewById(R.id.txt_endereco2);
        txt_email_contato= view.findViewById(R.id.txt_email);

        //resgata id do usuario
        if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
            Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_pessoa.getId();
        }
        else{
            Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_instituicao.getId();
        }

        //pega o id do evento da activity
        final NavDrawMenu act= (NavDrawMenu) getActivity();

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

        //pega no nó específico com o id do evento
        DatabaseReference dados_instituicao= BD.child("cadastroUsuarioEvento");
        dados_instituicao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot usuarios_cadastrados : dataSnapshot.getChildren()){

                        String id_evento_certo= act.getIdEvento();

                        Iterable<DataSnapshot> eventos_usuario_children= usuarios_cadastrados.getChildren();

                        //se o nó dentro de usuario tiver o id_evento que queremos
                        for(DataSnapshot eventos_children: eventos_usuario_children){
                            if(eventos_children.getKey().contentEquals(id_evento_certo)){
                                //pega os valores de cadastro
                                CadastroUsuarioEvento cadastroUsuarioEvento= eventos_children.getValue(CadastroUsuarioEvento.class);
                                setContentView2(cadastroUsuarioEvento);
                            }
                        }
                    }
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pega no nó específico para ver se evento está salvo
        DatabaseReference dados_evento_salvo= BD.child("salvaEvento").child(id_usuario).child(act.getIdEvento());
        dados_evento_salvo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    btn_salvar_evento.setImageResource(R.drawable.ic_salvo_evento);
                    btn_salvar_evento.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pega no nó específico para ver se evento está confirmado
        DatabaseReference dados_evento_confirmado= BD.child("confirmaEvento").child(id_usuario).child(act.getIdEvento());
        dados_evento_confirmado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    btn_confirmar_evento.setImageResource(R.drawable.ic_confirmado_evento);
                    btn_confirmar_evento.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //ação do botão Salvar
        btn_salvar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                alert_evento.setTitle("Deseja salvar evento?");
                alert_evento.setMessage("Salvá-lo pode te ajudar a lembrar dele");
                alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SalvaEvento evento_salvo= new SalvaEvento(id_usuario, act.getIdEvento(), lbl_titulo_evento.getText().toString());

                        SalvaEventoDAO salvaEventoDAO= new SalvaEventoDAO();
                        salvaEventoDAO.inserirEventoSalvo(evento_salvo);

                        btn_salvar_evento.setImageResource(R.drawable.ic_salvo_evento);
                        btn_salvar_evento.setEnabled(false);
                    }
                });

                alert_evento.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert_evento.create();
                alert_evento.show();
            }
        });

        //ação do botão Confirmar
        btn_confirmar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                alert_evento.setTitle("Deseja confirmar presença no evento?");
                alert_evento.setMessage("A confirmação do evento tem importância para o organizador do evento!");
                alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfirmaEvento evento_confirmado= new ConfirmaEvento(id_usuario, act.getIdEvento(), lbl_titulo_evento.getText().toString());

                        ConfirmaEventoDAO confirmaEventoDAO= new ConfirmaEventoDAO();
                        confirmaEventoDAO.inserirEventoConfirmado(evento_confirmado);

                        btn_confirmar_evento.setImageResource(R.drawable.ic_confirmado_evento);
                        btn_confirmar_evento.setEnabled(false);
                    }
                });

                alert_evento.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert_evento.create();
                alert_evento.show();
            }
        });

        btn_nome_instituicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_instituicao= lbl_id_instituicao.getText().toString();

                //transfere o id da instituicao para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdInstituicao(id_instituicao);

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilVerTodasInstituicoesFragment perfil_instituicao= new PerfilVerTodasInstituicoesFragment();
                ft.replace(R.id.place_holder_nav_draw, perfil_instituicao).addToBackStack(null);
                ft.commit();
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

    public void setContentView2 (CadastroUsuarioEvento cadastro){
        if(cadastro.getTipoUsuario().contentEquals("pessoa")){
            btn_nome_instituicao.setVisibility(View.INVISIBLE);
            lbl_organizado_por.setVisibility(View.INVISIBLE);
        }
        else{
            btn_nome_instituicao.setText(cadastro.getNomeInstituicao());
            lbl_id_instituicao.setText(cadastro.getIdUsuario());
        }

    }
}
