package com.projetointegrador.solidarize.VIEW;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawer.AcoesUsuarioEventosFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.AcoesUsuarioPedidosDoacaoFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.ConfiguracoesFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.FeedEventosFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.FeedInstituicoesFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.FeedPedidosDoacaoFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.PerfilUsuarioFragment;
import com.projetointegrador.solidarize.VIEW.NavDrawer.SobreAppFragment;

public class NavDrawMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();
    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    //para os perfis quando clicados
    private String id_evento;
    private String id_pedido_doacao;
    private String id_instituicao;

    //getters e setters
    public String getIdEvento() {
        return id_evento;
    }

    public void setIdEvento(String id_evento) {
        this.id_evento = id_evento;
    }

    public String getIdInstituicao() {
        return id_instituicao;
    }

    public void setIdInstituicao(String id_instituicao) {
        this.id_instituicao = id_instituicao;
    }

    public String getIdPedidoDoacao() {
        return id_pedido_doacao;
    }

    public void setIdPedidoDoacao(String id_pedido_doacao) {
        this.id_pedido_doacao = id_pedido_doacao;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pegando usuario e setando UsuarioLogado
        final String email_usuario= auth_usuario.getCurrentUser().getEmail();

        // Aqui vc vai pegar o email do usuario
        // Vai buscar dados do usuario no firebase conforme o tipo de usuario
        // Vai preencher UsuarioLogado
        //se for pessoa
        BD.child("pessoa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ) {
                    for ( DataSnapshot datasnapUsuario : dataSnapshot.getChildren() ) {
                        Pessoa p= datasnapUsuario.getValue(Pessoa.class);
                        if(p.getEmail().contentEquals(email_usuario)){
                            UsuarioLogado.getInstance().setUsuario(p);
                            /* para pegar infos de pessoa
                            if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
                                Pessoa p1 = (Pessoa) UsuarioLogado.getInstance().getUsuario();
                            }*/
                            break;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //se for instituicao
        BD.child("instituicao").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ) {
                    for ( DataSnapshot datasnapUsuario : dataSnapshot.getChildren() ) {
                        Instituicao i= datasnapUsuario.getValue(Instituicao.class);
                        if(i.getEmail().contentEquals(email_usuario)){
                            UsuarioLogado.getInstance().setUsuario(i);
                            /* para pegar infos de instituicao
                            if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("instituicao")) {
                                Instituicao i1 = (Instituicao) UsuarioLogado.getInstance().getUsuario();
                            }*/
                            break;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //montagem do NavDraw
        setContentView(R.layout.activity_nav_draw_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        FeedEventosFragment fef= new FeedEventosFragment();
        ft.replace(R.id.place_holder_nav_draw, fef);
        ft.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        switch (id) {
            case R.id.nav_perfil:
                PerfilUsuarioFragment puf= new PerfilUsuarioFragment();
                ft.replace(R.id.place_holder_nav_draw, puf).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_acoes_eventos:
                AcoesUsuarioEventosFragment auef= new AcoesUsuarioEventosFragment();
                ft.replace(R.id.place_holder_nav_draw, auef).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_acoes_pedidos_doacao:
                AcoesUsuarioPedidosDoacaoFragment aupdf= new AcoesUsuarioPedidosDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, aupdf).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_eventos:
                FeedEventosFragment fef= new FeedEventosFragment();
                ft.replace(R.id.place_holder_nav_draw, fef).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_inst_todas:
                FeedInstituicoesFragment fif= new FeedInstituicoesFragment();
                ft.replace(R.id.place_holder_nav_draw, fif).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_inst_pedidos:
                FeedPedidosDoacaoFragment fpdf= new FeedPedidosDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, fpdf).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_config:
                ConfiguracoesFragment cf= new ConfiguracoesFragment();
                ft.replace(R.id.place_holder_nav_draw, cf).addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_sobre_app:
                SobreAppFragment saf= new SobreAppFragment();
                ft.replace(R.id.place_holder_nav_draw, saf);
                ft.commit();
                break;

            case R.id.nav_sair:
                //esvazia usuario
                UsuarioLogado.getInstance().unsetUsuario();

                //user desloga
                auth_usuario.signOut();
                Toast.makeText(getApplicationContext(), "Usuário deslogado", Toast.LENGTH_SHORT).show();

                Intent i= new Intent();
                setResult(RESULT_OK, i);

                this.finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
