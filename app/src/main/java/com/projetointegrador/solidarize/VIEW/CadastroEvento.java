package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

public class CadastroEvento extends AppCompatActivity {
    private FrameLayout place_holder;

    private Evento evento= new Evento();
    /*private String nome_evento;
    private String dt_inicio;
    private String dt_final;
    private String hra_inicio;
    private String hra_final;
    private String tipo;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        place_holder= findViewById(R.id.place_holder_info_cadastro_evento);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroEventoInfosFragment cadastro_infos= new CadastroEventoInfosFragment();
        ft.replace(R.id.place_holder_info_cadastro_evento, cadastro_infos);
        ft.commit();
    }

    public void setInfos(String nome_evento, String dt_inicio, String dt_final, String hra_inicio, String hra_final, String tipo){
        this.evento.setNome(nome_evento);
        this.evento.setDt_inicio(dt_inicio);
        this.evento.setDt_fim(dt_final);
        this.evento.setHra_inicio(hra_inicio);
        this.evento.setHra_fim(hra_final);
        this.evento.setTipo(tipo);
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
