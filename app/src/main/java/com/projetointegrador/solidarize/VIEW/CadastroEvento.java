package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

public class CadastroEvento extends AppCompatActivity {
    private String nome_evento;
    private String dt_inicio;
    private String dt_final;
    private String hra_inicio;
    private String hra_final;
    private String tipo;

    private FrameLayout place_holder;

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

    public void setInfos(Evento evento){
        this.nome_evento= evento.getNome();
        this.dt_inicio= evento.getDt_inicio();
        this.dt_final= evento.getDt_fim();
        this.hra_inicio= evento.getHra_inicio();
        this.hra_final= evento.getHra_fim();
        this.tipo= evento.getTipo();
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public String getDt_inicio() {
        return dt_inicio;
    }

    public String getDt_final() {
        return dt_final;
    }

    public String getHra_inicio() {
        return hra_inicio;
    }

    public String getHra_final() {
        return hra_final;
    }

    public String getTipo() {
        return tipo;
    }
}
