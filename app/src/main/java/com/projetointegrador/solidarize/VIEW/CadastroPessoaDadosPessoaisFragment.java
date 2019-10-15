package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.TelefoneUsuario;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class CadastroPessoaDadosPessoaisFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    PessoaDAO pessoaDAO= new PessoaDAO();

    private EditText nome;
    private EditText email;
    private EditText cpf;
    private EditText telefone;
    private EditText dt_nasc;

    private Button btn_continuar;

    private String tipo;
    public CadastroPessoaDadosPessoaisFragment(String tipo){
        this.tipo= tipo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pessoa_dados_pessoais, container, false);

        nome= view.findViewById(R.id.txt_nome);
        email= view.findViewById(R.id.txt_email);
        cpf= view.findViewById(R.id.txt_cpf);
        telefone= view.findViewById(R.id.txt_telefone);
        dt_nasc= view.findViewById(R.id.txt_dt_nasc);
        btn_continuar= view.findViewById(R.id.btn_continuar_dados_pessoais_pessoa);

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n, e, c, t, d;
                    n= nome.getText().toString();
                    e= email.getText().toString();
                    c= cpf.getText().toString();
                    t= telefone.getText().toString();
                    d= dt_nasc.getText().toString();

                    CadastroPessoa act= (CadastroPessoa) getActivity();
                    act.setDadosPessoais(n, e, c, t, d);

                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaEnderecoFragment cadastro_endereco= new CadastroPessoaEnderecoFragment(CadastroPessoaEnderecoFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_endereco);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroPessoa act= (EdicaoCadastroPessoa) getActivity();

            String id= "-LqvLtlbg9ISOM4dr8Rk";

            DatabaseReference pessoa_dados= pessoaDAO.getUsuarioPessoaNo(id);
            //recuperacao de dados do firebase
            pessoa_dados.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if ( dataSnapshot.exists() ) {
                        Pessoa p1= dataSnapshot.getValue(Pessoa.class);

                        String n, e, s, c, t, dt, ci, es;

                        n= p1.getNome();
                        e= p1.getEmail();
                        s= p1.getSenha();
                        c= p1.getCpf();
                        t= p1.getTelefone();
                        dt= p1.getData_nasc();
                        ci= p1.getCidade();
                        es= p1.getEstado();

                        act.setDadosPessoais(n, e, c, t, dt);
                        act.setEndereco(ci, es);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //Toast.makeText(act.getApplicationContext(), act.getPessoa().getNome(), Toast.LENGTH_SHORT).show();
            nome.setText(act.getPessoa().getNome());
            email.setText(act.getPessoa().getEmail());
            cpf.setText(act.getPessoa().getCpf());
            telefone.setText(act.getPessoa().getTelefone());
            dt_nasc.setText(act.getPessoa().getData_nasc());

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n, e, c, t, d;
                    n= nome.getText().toString();
                    e= email.getText().toString();
                    c= cpf.getText().toString();
                    t= telefone.getText().toString();
                    d= dt_nasc.getText().toString();

                    act.setDadosPessoais(n, e, c, t, d);

                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaEnderecoFragment cadastro_endereco= new CadastroPessoaEnderecoFragment(CadastroPessoaEnderecoFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_pessoa, cadastro_endereco);
                    ft.commit();
                }
            });
        }



        return view;
    }
}
