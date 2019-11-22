package com.projetointegrador.solidarize.DAO;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroEvento;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroInstituicao;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroPedidoDoacao;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroPessoa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;

public class LoadSpinnerEstadoCidade {
    private Fragment fragment;
    private String tipo_fragment;
    private String tipo_cadastro;
    private HashMap<String, Integer> lista_estados;
    private ArrayList<String> lista_adapter_estados;
    private ArrayList<String> lista_adapter_cidades;
    private Spinner spin_estados;
    private Spinner spin_cidades;

    public LoadSpinnerEstadoCidade(Fragment fragment, String tipo_fragment, String tipo_cadastro) {
        this.fragment= fragment;
        this.tipo_fragment= tipo_fragment;
        this.tipo_cadastro= tipo_cadastro;
    }

    public void setInfosEstados(HashMap<String, Integer> lista_estados, ArrayList<String> lista_adapter_estados, Spinner spin_estados){
        this.lista_estados = lista_estados;
        this.lista_adapter_estados = lista_adapter_estados;
        this.spin_estados = spin_estados;
    }

    public void setInfosCidades(ArrayList<String> lista_adapter_cidades, Spinner spin_cidades) {
        this.lista_adapter_cidades = lista_adapter_cidades;
        this.spin_cidades = spin_cidades;
    }

    public void loadSpinnerEstados(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(fragment.getActivity().getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray("");
                    JSONArray jsonArray = new JSONArray(response);

                    lista_adapter_estados.add("Selecione um estado...");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonEstado = jsonArray.getJSONObject(i);
                        int cod_estado = jsonEstado.getInt("id");
                        String nome_estado = jsonEstado.getString("nome");

                        lista_estados.put(nome_estado, cod_estado);
                        int posicao_lista_adapter= i+1;
                        lista_adapter_estados.add(nome_estado);
                    }

                    spin_estados.setAdapter(new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_spinner_dropdown_item, lista_adapter_estados));

                    if(tipo_cadastro.contentEquals("edicao")){
                        int position_estado= 0;

                        switch(tipo_fragment){
                            case "pessoa":
                                EdicaoCadastroPessoa act_p= (EdicaoCadastroPessoa) fragment.getActivity();
                                position_estado= lista_adapter_estados.indexOf(act_p.getPessoa().getEstado());
                                break;

                            case "instituicao":
                                EdicaoCadastroInstituicao act_i= (EdicaoCadastroInstituicao) fragment.getActivity();
                                position_estado= lista_adapter_estados.indexOf(act_i.getInstituicao().getEstado());
                                break;

                            case "evento":
                                EdicaoCadastroEvento act_e= (EdicaoCadastroEvento) fragment.getActivity();
                                position_estado= lista_adapter_estados.indexOf(act_e.getEvento().getEstado());
                                break;

                            case "pedidoDoacao":
                                EdicaoCadastroPedidoDoacao act_pe= (EdicaoCadastroPedidoDoacao) fragment.getActivity();
                                position_estado= lista_adapter_estados.indexOf(act_pe.getPedido_de_doacao().getEstado());
                                break;

                            default:
                                position_estado= 0;
                                break;
                        }

                        spin_estados.setSelection(position_estado);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void loadSpinnerCidades(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(fragment.getActivity().getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    lista_adapter_cidades.add("Selecione sua cidade...");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonCidade = jsonArray.getJSONObject(i);
                        String nome_cidade = jsonCidade.getString("nome");

                        int posicao_lista_adapter= i+1;
                        lista_adapter_cidades.add(nome_cidade);
                    }

                    spin_cidades.setAdapter(new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_spinner_dropdown_item, lista_adapter_cidades));

                    if(tipo_cadastro.contentEquals("edicao")){
                        int position_cidade= 0;

                        switch(tipo_fragment){
                            case "pessoa":
                                EdicaoCadastroPessoa act_p= (EdicaoCadastroPessoa) fragment.getActivity();
                                position_cidade= lista_adapter_cidades.indexOf(act_p.getPessoa().getCidade());
                                break;

                            case "instituicao":
                                EdicaoCadastroInstituicao act_i= (EdicaoCadastroInstituicao) fragment.getActivity();
                                position_cidade= lista_adapter_cidades.indexOf(act_i.getInstituicao().getCidade());
                                break;

                            case "evento":
                                EdicaoCadastroEvento act_e= (EdicaoCadastroEvento) fragment.getActivity();
                                position_cidade= lista_adapter_cidades.indexOf(act_e.getEvento().getCidade());
                                break;

                            case "pedidoDoacao":
                                EdicaoCadastroPedidoDoacao act_pe= (EdicaoCadastroPedidoDoacao) fragment.getActivity();
                                position_cidade= lista_adapter_cidades.indexOf(act_pe.getPedido_de_doacao().getCidade());
                                break;

                            default:
                                position_cidade= 0;
                                break;
                        }

                        spin_cidades.setSelection(position_cidade);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}
