package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.InstituicaoDAO;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class ConfiguracoesFragment extends Fragment {
    private Button btn_excluir_conta;

    FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_configuracoes, container, false);

        btn_excluir_conta= view.findViewById(R.id.btn_excluir_conta);

        btn_excluir_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth_usuario.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
                                Pessoa pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
                                PessoaDAO pessoaDAO= new PessoaDAO();
                                pessoaDAO.excluirUsuarioPessoa(pessoa.getId());
                            }
                            else {
                                Instituicao instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
                                InstituicaoDAO instituicaoDAO= new InstituicaoDAO();
                                instituicaoDAO.excluirUsuarioInstituicao(instituicao.getId());
                            }

                            getActivity().finish();
                        }
                    }
                });
            }
        });

        return view;
    }
}
