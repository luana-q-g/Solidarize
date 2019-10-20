package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.InstituicaoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroInstituicaoDadosPessoaisFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroInstituicaoDadosPessoaisFragment(String tipo){
        this.tipo= tipo;
    }

    private InstituicaoDAO instituicaoDAO= new InstituicaoDAO();

    private EditText nome;
    private EditText email;
    private EditText cnpj;
    private EditText telefone;

    private Button btn_continuar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_instituicao_dados_pessoais, container, false);

        nome= view.findViewById(R.id.txt_nome);
        email= view.findViewById(R.id.txt_email);
        cnpj= view.findViewById(R.id.txt_cnpj);
        telefone= view.findViewById(R.id.txt_telefone);
        btn_continuar= view.findViewById(R.id.btn_continuar_dados_pessoais_instituicao);

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n, e, c, t, d;
                    n= nome.getText().toString();
                    e= email.getText().toString();
                    c= cnpj.getText().toString();
                    t= telefone.getText().toString();

                    CadastroInstituicao act= (CadastroInstituicao) getActivity();
                    act.setDadosPessoais(n, e, c, t);

                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroInstituicaoEnderecoFragment cadastro_endereco= new CadastroInstituicaoEnderecoFragment(CadastroInstituicaoEnderecoFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_instituicao, cadastro_endereco);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroInstituicao act= (EdicaoCadastroInstituicao) getActivity();

            if(auth_usuario.getCurrentUser() != null) {
                //recupera dados de usuario logado
                UsuarioLogado.getInstance().getUsuario();
                Instituicao instituicao_logada = (Instituicao) UsuarioLogado.getInstance().getUsuario();

                String n, e, i, c, t, d, ci, es, ru, num;

                n = instituicao_logada.getNome();
                e = instituicao_logada.getEmail();
                i = instituicao_logada.getId();
                c = instituicao_logada.getCnpj();
                t = instituicao_logada.getTelefone();
                d = instituicao_logada.getDescricao();
                ci = instituicao_logada.getCidade();
                es = instituicao_logada.getEstado();
                ru = instituicao_logada.getRua();
                num = instituicao_logada.getNumero();

                act.setEndereco(ci, es, ru, num);
                act.setDescricao(d);
                act.setId(i);

                setDadosView(n, e, c, t);


                btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n, e, c, t;
                        n = nome.getText().toString();
                        e = email.getText().toString();
                        c = cnpj.getText().toString();
                        t = telefone.getText().toString();

                        EdicaoCadastroInstituicao act = (EdicaoCadastroInstituicao) getActivity();
                        act.setDadosPessoais(n, e, c, t);

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();

                        CadastroInstituicaoEnderecoFragment cadastro_endereco = new CadastroInstituicaoEnderecoFragment(CadastroInstituicaoEnderecoFragment.EDICAO);
                        ft.replace(R.id.place_holder_info_edicao_cadastro_instituicao, cadastro_endereco);
                        ft.commit();
                    }
                });
            }
        }



        return view;
    }

    public void setDadosView(String n, String e, String c, String t) {
        nome.setText(n);
        email.setText(e);
        //desabilita email, pois ele não pode ser editado
        email.setEnabled(false);
        //desabilita cpf, pois ele não pode ser editado
        cnpj.setText(c);
        cnpj.setEnabled(false);
        telefone.setText(t);
    }
}
