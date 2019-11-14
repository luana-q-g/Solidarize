package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PerfilPedidoDoacaoFragment extends Fragment {
    private TextView lbl_titulo_item_pedido;
    private ProgressBar bar_meta_pedido;
    private TextView txt_tipo_pedido;
    private ProgressBar bar_nivel_urgencia;
    private TextView lbl_data_validade;
    private TextView lbl_locais;
    private Button btn_nome_instituicao;
    private TextView txt_id_instituicao;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_perfil_pedidos_de_doacao, container, false);

        lbl_titulo_item_pedido= view.findViewById(R.id.lbl_item_pedido);
        bar_meta_pedido= view.findViewById(R.id.bar_meta_dos_pedidos);
        txt_tipo_pedido= view.findViewById(R.id.txt_tipo_item);
        bar_nivel_urgencia= view.findViewById(R.id.bar_nivel_urgencia_pedido);
        lbl_data_validade= view.findViewById(R.id.txt_dt_validade);
        lbl_locais= view.findViewById(R.id.txt_locais_arrecadacao);
        btn_nome_instituicao= view.findViewById(R.id.btn_nome_instituicao);
        txt_id_instituicao= view.findViewById(R.id.txt_id_instituicao);

        //pega o id do evento da activity
        final NavDrawMenu act= (NavDrawMenu) getActivity();

        //pega no nó específico com o id do pedido
        DatabaseReference dados_pedido= BD.child("pedidoDeDoacao").child(act.getIdPedidoDoacao());
        dados_pedido.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PedidoDeDoacao pedido= dataSnapshot.getValue(PedidoDeDoacao.class);

                setContentView(pedido);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pega no nó específico com o id do pedido
        DatabaseReference dados_instituicao= BD.child("cadastroUsuarioPedido");
        dados_instituicao.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot usuarios_cadastrados : dataSnapshot.getChildren()){

                        String id_pedido_certo= act.getIdPedidoDoacao();

                        Iterable<DataSnapshot> pedidos_usuario_children= usuarios_cadastrados.getChildren();

                        //se o nó dentro de usuario tiver o id_pedido que queremos
                        for(DataSnapshot pedidos_children: pedidos_usuario_children){
                            if(pedidos_children.getKey().contentEquals(id_pedido_certo)){
                                //pega os valores de cadastro
                                CadastroUsuarioPedidoDoacao cadastroUsuarioPedidoDoacao= pedidos_children.getValue(CadastroUsuarioPedidoDoacao.class);
                                setContentView2(cadastroUsuarioPedidoDoacao);
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

        return view;
    }

    public void setContentView (PedidoDeDoacao pedido){
        //definir meta e urgencia
        lbl_titulo_item_pedido.setText(pedido.getItem());
        lbl_data_validade.setText(pedido.getDt_validade());
        txt_tipo_pedido.setText(pedido.getTipo_pedido());
        String endereco= pedido.getEstado()+", "+pedido.getCidade()+", "+pedido.getRua()+", "+pedido.getComplemento();
        lbl_locais.setText(endereco);
    }

    public void setContentView2 (CadastroUsuarioPedidoDoacao cadastro){
        btn_nome_instituicao.setText(cadastro.getNomeInstituicao());
        txt_id_instituicao.setText(cadastro.getIdUsuario());

    }
}
