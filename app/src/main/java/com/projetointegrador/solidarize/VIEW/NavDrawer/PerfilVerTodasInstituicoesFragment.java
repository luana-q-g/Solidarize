package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PerfilVerTodasInstituicoesFragment extends Fragment {
    private TextView lbl_nome_instituicao;
    private TextView lbl_descricao;
    private TextView txt_email;
    private TextView txt_telefone;
    private TextView txt_cnpj;
    private TextView txt_endereco;
    private TextView lbl_existencia_pedidos;
    private ListView lista_pedidos_instituicao;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_perfil_ver_todas_instituicoes, container, false);

        lbl_nome_instituicao= view.findViewById(R.id.lbl_nome_instituicao);
        lbl_descricao= view.findViewById(R.id.lbl_descricao);
        txt_email= view.findViewById(R.id.txt_email);
        txt_telefone= view.findViewById(R.id.txt_telefone);
        txt_cnpj= view.findViewById(R.id.txt_cnpj);
        txt_endereco= view.findViewById(R.id.txt_endereco);
        lbl_existencia_pedidos= view.findViewById(R.id.lbl_existencia_pedidos);
        lista_pedidos_instituicao= view.findViewById(R.id.lista_pedidos_instituicao);

        //pega o id da instituicao da activity
        final NavDrawMenu act= (NavDrawMenu) getActivity();

        //pega no nó específico com o id da instituicao
        DatabaseReference dados_instituicao= BD.child("instituicao").child(act.getIdInstituicao());
        dados_instituicao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Instituicao instituicao= dataSnapshot.getValue(Instituicao.class);

                setContentView(instituicao);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pega no nó específico com o id dos pedidos
        DatabaseReference dados_pedidos= BD.child("cadastroUsuarioPedido").child(act.getIdInstituicao());

        FirebaseListOptions<CadastroUsuarioPedidoDoacao> pedidos_options= new FirebaseListOptions.Builder<CadastroUsuarioPedidoDoacao>()
                .setLayout(R.layout.item_edicao_pedidos_doacao)
                .setQuery(dados_pedidos, CadastroUsuarioPedidoDoacao.class)
                .setLifecycleOwner(this)
                .build();

        AdapterListaPerfilPedidosDoacao adapter = new AdapterListaPerfilPedidosDoacao(pedidos_options);
        lista_pedidos_instituicao.setAdapter(adapter);

        //verifica se existem pedidos
        if(adapter.getCount() == 0){
            lbl_existencia_pedidos.setText("");
        }

        return view;
    }

    public void setContentView (Instituicao instituicao){
        lbl_nome_instituicao.setText(instituicao.getNome());
        lbl_descricao.setText(instituicao.getDescricao());
        txt_email.setText(instituicao.getEmail());
        txt_telefone.setText(instituicao.getTelefone());
        txt_cnpj.setText(instituicao.getCnpj());
        txt_endereco.setText(instituicao.getEstado()+", "+instituicao.getCidade()+", "+instituicao.getRua()+", "+instituicao.getNumero());
    }
}
