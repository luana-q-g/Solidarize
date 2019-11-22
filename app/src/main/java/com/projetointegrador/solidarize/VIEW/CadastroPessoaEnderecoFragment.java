package com.projetointegrador.solidarize.VIEW;

import android.app.DownloadManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroPessoaEnderecoFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroPessoaEnderecoFragment(String tipo){
        this.tipo= tipo;
    }

    private Spinner spin_estado;
    private Spinner spin_cidade;

    private Button btn_voltar;
    private Button btn_continuar;

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
        //inflando fragment com seu layout
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pessoa_endereco, container, false);

        spin_estado= view.findViewById(R.id.spin_estados);
        spin_cidade= view.findViewById(R.id.spin_cidades);
        btn_voltar= view.findViewById(R.id.btn_voltar_endereco_pessoa);
        btn_continuar= view.findViewById(R.id.btn_continuar_endereco_pessoa);

        String URL_estados="https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        loadSpinner= new LoadSpinnerEstadoCidade(this, tipo);
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

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e;

                    e= spin_estado.getItemAtPosition(spin_estado.getSelectedItemPosition()).toString();
                    c= spin_cidade.getItemAtPosition(spin_cidade.getSelectedItemPosition()).toString();

                    CadastroPessoa act= (CadastroPessoa) getActivity();
                    act.setEndereco(c, e);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaSenhaFragment cadastro_senha= new CadastroPessoaSenhaFragment();
                    ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_senha);
                    ft.commit();
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment(CadastroPessoaDadosPessoaisFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_pessoa, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroPessoa act= (EdicaoCadastroPessoa) getActivity();

            btn_continuar.setText("Editar");

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c, e;

                    e= spin_estado.getItemAtPosition(spin_estado.getSelectedItemPosition()).toString();
                    c= spin_cidade.getItemAtPosition(spin_cidade.getSelectedItemPosition()).toString();

                    act.setEndereco(c, e);

                    PessoaDAO pessoaDao= new PessoaDAO();
                    pessoaDao.alterarUsuarioPessoa(act.getPessoa());

                    UsuarioLogado.getInstance().setUsuario(act.getPessoa());

                    act.finish();
                }
            });

            btn_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm= getActivity().getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPessoaDadosPessoaisFragment cadastro_dados_pessoais= new CadastroPessoaDadosPessoaisFragment(CadastroPessoaDadosPessoaisFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_pessoa, cadastro_dados_pessoais);
                    ft.commit();
                }
            });
        }

        return view;
    }
}
