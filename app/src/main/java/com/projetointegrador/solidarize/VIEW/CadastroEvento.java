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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        getSupportActionBar().setTitle("Cadastro de Evento");

        place_holder= findViewById(R.id.place_holder_info_cadastro_evento);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroEventoInfosFragment cadastro_infos= new CadastroEventoInfosFragment(CadastroEventoInfosFragment.CADASTRO);
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

    public void setEnderecoEFim(String email, String es, String ci, String rua, String com, String descri, String max){
        this.evento.setEmail_usuario(email);
        this.evento.setEstado(es);
        this.evento.setCidade(ci);
        this.evento.setRua(rua);
        this.evento.setNumero(com);
        this.evento.setDescricao(descri);
        this.evento.setMax_participantes(max);
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
