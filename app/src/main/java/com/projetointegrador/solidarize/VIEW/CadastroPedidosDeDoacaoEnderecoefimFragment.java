package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.CadastroUsuarioPedidoDoacaoDAO;
import com.projetointegrador.solidarize.DAO.PedidosDeDoacaoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroPedidosDeDoacaoEnderecoefimFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroPedidosDeDoacaoEnderecoefimFragment(String tipo){
        this.tipo= tipo;
    }

    private Spinner txt_estado;
    private Spinner txt_cidade;
    private EditText txt_rua;
    private EditText txt_complemento;

    private Button btn_voltar;
    private Button btn_cadastrar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    //para CadastroPedido
    private String id_usuario= "";
    private String nome_instituicao= "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pedidos_de_doacao_enderecoefim, container, false);

        txt_estado= view.findViewById(R.id.spin_estados);
        txt_cidade= view.findViewById(R.id.spin_cidades);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        btn_voltar= view.findViewById(R.id.btn_voltar_enderecoefim_pedidos_doacao);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_enderecoefim_pedidos_doacao);

        //recupera id do usuario
        //só pode ser uma instituicao para estar cadastrando um pedido
        Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
        id_usuario= usuario_instituicao.getId();
        nome_instituicao= usuario_instituicao.getNome();


        if(tipo.contentEquals(CADASTRO)){
            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(auth_usuario.getCurrentUser() != null){
                        String e, c, r, com, email_usuario;
                        //e= txt_estado.getSelectedItem().toString();
                        //c= txt_cidade.getSelectedItem().toString();
                        e= "";
                        c= "";
                        r= txt_rua.getText().toString();
                        com= txt_complemento.getText().toString();

                        email_usuario= auth_usuario.getCurrentUser().getEmail();

                        //cadastro
                        CadastroPedidosDeDoacao cadastro= (CadastroPedidosDeDoacao) getActivity();
                        cadastro.setEnderecoEFim(email_usuario, e, c, r, com);

                        PedidosDeDoacaoDAO pedidoDao= new PedidosDeDoacaoDAO();
                        pedidoDao.inserirPedidoDeDoacao(cadastro.getPedido_de_doacao());

                        //verificando tipo de usuario e fzendo cadastro de acordocom cada um
                        CadastroUsuarioPedidoDoacao relacao_usuario_pedido= new CadastroUsuarioPedidoDoacao(id_usuario, cadastro.getPedido_de_doacao().getId(), cadastro.getPedido_de_doacao().getItem(), nome_instituicao);

                        //inserindo relação tipo "foreign key" para identificar que usuario que cria tal pedido
                        CadastroUsuarioPedidoDoacaoDAO relacao_usuario_pedidoDAO= new CadastroUsuarioPedidoDoacaoDAO();
                        relacao_usuario_pedidoDAO.inserirCadastroUsuarioPedido(relacao_usuario_pedido);

                        Toast.makeText(getContext(), "Pedido de doação cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                        getActivity().finish();
                    }
                    else{

                    }
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPedidosDeDoacaoInfosFragment cadastro_infos= new CadastroPedidosDeDoacaoInfosFragment(CadastroPedidosDeDoacaoInfosFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_pedidos_de_doacao, cadastro_infos);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            btn_cadastrar.setText("Editar");

            final EdicaoCadastroPedidoDoacao act= (EdicaoCadastroPedidoDoacao) getActivity();
            //txt_estado
            //txt_cidade
            txt_rua.setText(act.getPedido_de_doacao().getRua());
            txt_complemento.setText(act.getPedido_de_doacao().getComplemento());

            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(auth_usuario.getCurrentUser() != null){
                        String e, c, r, com, email_usuario;
                        //e= txt_estado.getSelectedItem().toString();
                        //c= txt_cidade.getSelectedItem().toString();
                        e= "";
                        c= "";
                        r= txt_rua.getText().toString();
                        com= txt_complemento.getText().toString();

                        email_usuario= auth_usuario.getCurrentUser().getEmail();

                        //edicao
                        EdicaoCadastroPedidoDoacao edicao= (EdicaoCadastroPedidoDoacao) getActivity();

                        //seta atributos de eventos que faltavam
                        edicao.setEnderecoEFim(email_usuario, e, c, r, com);

                        PedidosDeDoacaoDAO pedidosDeDoacaoDAO= new PedidosDeDoacaoDAO();
                        pedidosDeDoacaoDAO.alterarPedidoDoacao(edicao.getPedido_de_doacao());

                        //verificando tipo de usuario e fzendo cadastro de acordo com cada um
                        CadastroUsuarioPedidoDoacao relacao_usuario_pedido= new CadastroUsuarioPedidoDoacao(id_usuario, edicao.getPedido_de_doacao().getId(), edicao.getPedido_de_doacao().getItem(), nome_instituicao);

                        //inserindo relação tipo "foreign key" para identificar que usuario que cria tal pedido
                        CadastroUsuarioPedidoDoacaoDAO relacao_usuario_pedidoDAO= new CadastroUsuarioPedidoDoacaoDAO();
                        relacao_usuario_pedidoDAO.alterarCadastroUsuarioPedido(relacao_usuario_pedido);


                        Toast.makeText(getContext(), "Pedido de Doação alterado com sucesso!", Toast.LENGTH_SHORT).show();

                        getActivity().finish();
                    }
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPedidosDeDoacaoInfosFragment cadastro_infos= new CadastroPedidosDeDoacaoInfosFragment(CadastroPedidosDeDoacaoInfosFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_pedido_doacao, cadastro_infos);
                    ft.commit();
                }
            });
        }


        return view;
    }
}
