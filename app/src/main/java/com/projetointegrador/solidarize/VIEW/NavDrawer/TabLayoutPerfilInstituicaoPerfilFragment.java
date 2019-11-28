package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroInstituicao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static android.app.Activity.RESULT_OK;

public class TabLayoutPerfilInstituicaoPerfilFragment extends Fragment {
    private TextView txt_nome, txt_email, txt_cnpj, txt_descricao, txt_tel, txt_estado, txt_cidade, txt_rua, txt_complemento;
    private Button btn_editar;

    //para o activity for result
    //quando termina o editar, atualiza o perfil
    public static final int EDITAR_PERFIL_INSTITUICAO = 8;
    public static final String INSTITUICAO_EDITADA = "INSTITUICAO_EDITADA";

    private boolean allowRefresh= false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_instituicao_perfil, container, false);

        txt_nome= view.findViewById(R.id.txt_nome_instituicao);
        txt_email= view.findViewById(R.id.txt_email);
        txt_cnpj= view.findViewById(R.id.txt_cnpj);
        txt_descricao= view.findViewById(R.id.txt_descricao_instituicao);
        txt_tel= view.findViewById(R.id.txt_telefone);
        txt_estado= view.findViewById(R.id.txt_estado);
        txt_cidade= view.findViewById(R.id.txt_cidade);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        btn_editar= view.findViewById(R.id.btn_editar);

        Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();

        //funcao seta dados nos campos do perfil
        setDadosView(usuario_instituicao.getNome(), usuario_instituicao.getEmail(), usuario_instituicao.getCnpj(), usuario_instituicao.getTelefone(), usuario_instituicao.getDescricao(), usuario_instituicao.getEstado(), usuario_instituicao.getCidade(), usuario_instituicao.getRua(), usuario_instituicao.getNumero());

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre activity com info de pessoa para editar
                Intent i_inst= new Intent(getActivity().getApplicationContext(), EdicaoCadastroInstituicao.class);
                startActivityForResult(i_inst, EDITAR_PERFIL_INSTITUICAO);
            }
        });

        return view;
    }

    //depois de editar o perfil
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDITAR_PERFIL_INSTITUICAO){
            if (resultCode == RESULT_OK) {
                Instituicao instEditada = (Instituicao) data.getSerializableExtra(INSTITUICAO_EDITADA);
                setDadosView(
                        instEditada.getNome(),
                        instEditada.getEmail(),
                        instEditada.getCnpj(),
                        instEditada.getTelefone(),
                        instEditada.getDescricao(),
                        instEditada.getEstado(),
                        instEditada.getCidade(),
                        instEditada.getRua(),
                        instEditada.getNumero()
                );
            }
        }
    }

    public void setDadosView(String n, String e, String c, String t, String dt, String es, String ci, String rua, String com) {
        txt_nome.setText(n);
        txt_email.setText(e);
        txt_cnpj.setText(c);
        txt_tel.setText(t);
        txt_descricao.setText(dt);
        txt_cidade.setText(ci);
        txt_estado.setText(es);
        txt_rua.setText(rua);
        txt_complemento.setText(com);
    }

}
