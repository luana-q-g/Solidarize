package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.CadastroUsuarioEventoDAO;
import com.projetointegrador.solidarize.DAO.EventoDAO;
import com.projetointegrador.solidarize.DAO.LoadSpinnerEstadoCidade;
import com.projetointegrador.solidarize.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroEventoEnderecoefimFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroEventoEnderecoefimFragment(String tipo){
        this.tipo= tipo;
    }

    private Spinner spin_estado;
    private Spinner spin_cidade;
    private EditText txt_rua;
    private EditText txt_complemento;
    private EditText txt_descricao;
    private EditText txt_max_participantes;

    private Button btn_voltar;
    private Button btn_cadastrar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    //para CadastroEvento
    private String id_usuario= "";
    private String tipo_usuario= "";
    private String nome_instituicao= "";

    //loadSpinner
    LoadSpinnerEstadoCidade loadSpinner;
    HashMap<String, Integer> lista_estados= new HashMap<String, Integer>();
    ArrayList<String> lista_adapter_estados= new ArrayList<>();
    ArrayList<String> lista_adapter_cidades= new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_evento_enderecoefim, container, false);

        spin_estado= view.findViewById(R.id.spin_estados);
        spin_cidade= view.findViewById(R.id.spin_cidades);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        txt_descricao= view.findViewById(R.id.txt_descricao_evento);
        txt_max_participantes= view.findViewById(R.id.txt_max_participantes);
        btn_voltar= view.findViewById(R.id.btn_voltar_enderecoefim_eventos);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_enderecoefim_eventos);

        //loadSpinner
        String URL_estados="https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        loadSpinner= new LoadSpinnerEstadoCidade(this, "evento", tipo);
        loadSpinner.setInfosEstados(lista_estados, lista_adapter_estados, spin_estado);
        loadSpinner.loadSpinnerEstados(URL_estados);

        spin_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nome_estado= spin_estado.getItemAtPosition(spin_estado.getSelectedItemPosition()).toString();

                Integer cod_estado= lista_estados.get(nome_estado);

                String URL_cidades_por_estado="https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+cod_estado+"/municipios";
                loadSpinner.setInfosCidades(lista_adapter_cidades, spin_cidade);
                loadSpinner.loadSpinnerCidades(URL_cidades_por_estado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //recupera id do usuario
        if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
            Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_pessoa.getId();
            tipo_usuario= "pessoa";
        }
        else{
            Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_instituicao.getId();
            tipo_usuario= "instituicao";
            nome_instituicao= usuario_instituicao.getNome();
        }

        if(tipo.contentEquals(CADASTRO)){
            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(auth_usuario.getCurrentUser() != null){
                        String e, c, r, com, d, m, email_usuario;

                        e= spin_estado.getItemAtPosition(spin_estado.getSelectedItemPosition()).toString();
                        c= spin_cidade.getItemAtPosition(spin_cidade.getSelectedItemPosition()).toString();

                        r= txt_rua.getText().toString();
                        com= txt_complemento.getText().toString();
                        d= txt_descricao.getText().toString();
                        m= txt_max_participantes.getText().toString();

                        email_usuario= auth_usuario.getCurrentUser().getEmail();

                        //cadastro
                        CadastroEvento cadastro= (CadastroEvento) getActivity();
                        Evento evento= cadastro.getEvento();

                        cadastro.setEnderecoEFim(email_usuario, e, c, r, com, d, m);

                        EventoDAO eventoDao= new EventoDAO();
                        eventoDao.inserirEvento(evento);

                        //verificando tipo de usuario e fzendo cadastro de acordocom cada um
                        CadastroUsuarioEvento relacao_usuario_evento;
                        if(tipo_usuario.contentEquals("pessoa")){
                            relacao_usuario_evento= new CadastroUsuarioEvento(id_usuario, cadastro.getEvento().getId(), cadastro.getEvento().getNome(), "", tipo_usuario);
                        }
                        else{
                            relacao_usuario_evento= new CadastroUsuarioEvento(id_usuario, cadastro.getEvento().getId(), cadastro.getEvento().getNome(), nome_instituicao, tipo_usuario);
                        }

                        //inserindo relação tipo "foreign key" para identificar que usuario que cria tal evento
                        CadastroUsuarioEventoDAO relacao_usuario_eventoDAO= new CadastroUsuarioEventoDAO();
                        relacao_usuario_eventoDAO.inserirCadastroUsuarioEvento(relacao_usuario_evento);

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

                    CadastroEventoInfosFragment cadastro_infos= new CadastroEventoInfosFragment(CadastroEventoInfosFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_evento, cadastro_infos);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            btn_cadastrar.setText("Editar");

            final EdicaoCadastroEvento act= (EdicaoCadastroEvento) getActivity();

            //estado e cidade selecionados pelo LoadSpinner
            txt_rua.setText(act.getEvento().getRua());
            txt_complemento.setText(act.getEvento().getNumero());
            txt_descricao.setText(act.getEvento().getDescricao());
            txt_max_participantes.setText(act.getEvento().getMax_participantes());

            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(auth_usuario.getCurrentUser() != null){
                        String e, c, r, com, d, m, email_usuario;

                        e= spin_estado.getItemAtPosition(spin_estado.getSelectedItemPosition()).toString();
                        c= spin_cidade.getItemAtPosition(spin_cidade.getSelectedItemPosition()).toString();

                        r= txt_rua.getText().toString();
                        com= txt_complemento.getText().toString();
                        d= txt_descricao.getText().toString();
                        m= txt_max_participantes.getText().toString();

                        email_usuario= auth_usuario.getCurrentUser().getEmail();

                        //edicao
                        EdicaoCadastroEvento edicao= (EdicaoCadastroEvento) getActivity();

                        //seta atributos de eventos que faltavam
                        edicao.setEnderecoEFim(email_usuario, e, c, r, com, d, m);

                        EventoDAO eventoDao= new EventoDAO();
                        eventoDao.alterarEvento(edicao.getEvento());


                        //verificando tipo de usuario e fzendo cadastro de acordocom cada um
                        CadastroUsuarioEvento relacao_usuario_evento;
                        if(tipo_usuario.contentEquals("pessoa")){
                            relacao_usuario_evento= new CadastroUsuarioEvento(id_usuario, edicao.getEvento().getId(), edicao.getEvento().getNome(), "", tipo_usuario);
                        }
                        else{
                            relacao_usuario_evento= new CadastroUsuarioEvento(id_usuario, edicao.getEvento().getId(), edicao.getEvento().getNome(), nome_instituicao, tipo_usuario);
                        }
                        //editando "foreign key"
                        CadastroUsuarioEventoDAO relacao_usuario_eventoDAO= new CadastroUsuarioEventoDAO();
                        relacao_usuario_eventoDAO.alterarCadastroUsuarioEvento(relacao_usuario_evento);

                        Toast.makeText(getContext(), "Evento alterado com sucesso!", Toast.LENGTH_SHORT).show();

                        getActivity().finish();
                    }
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroEventoInfosFragment cadastro_infos= new CadastroEventoInfosFragment(CadastroEventoInfosFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_evento, cadastro_infos);
                    ft.commit();
                }
            });
        }

        return view;
    }
}
