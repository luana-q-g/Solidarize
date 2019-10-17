package com.projetointegrador.solidarize.VIEW;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                ft.replace(R.id.place_holder_nav_draw, puf);
                ft.commit();
                break;

            case R.id.nav_acoes_eventos:
                AcoesUsuarioEventosFragment auef= new AcoesUsuarioEventosFragment();
                ft.replace(R.id.place_holder_nav_draw, auef);
                ft.commit();
                break;

            case R.id.nav_acoes_pedidos_doacao:
                AcoesUsuarioPedidosDoacaoFragment aupdf= new AcoesUsuarioPedidosDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, aupdf);
                ft.commit();
                break;

            case R.id.nav_eventos:
                FeedEventosFragment fef= new FeedEventosFragment();
                ft.replace(R.id.place_holder_nav_draw, fef);
                ft.commit();
                break;

            case R.id.nav_inst_todas:
                FeedInstituicoesFragment fif= new FeedInstituicoesFragment();
                ft.replace(R.id.place_holder_nav_draw, fif);
                ft.commit();
                break;

            case R.id.nav_inst_pedidos:
                FeedPedidosDoacaoFragment fpdf= new FeedPedidosDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, fpdf);
                ft.commit();
                break;

            case R.id.nav_config:
                ConfiguracoesFragment cf= new ConfiguracoesFragment();
                ft.replace(R.id.place_holder_nav_draw, cf);
                ft.commit();
                break;

            case R.id.nav_sobre_app:
                SobreAppFragment saf= new SobreAppFragment();
                ft.replace(R.id.place_holder_nav_draw, saf);
                ft.commit();
                break;

            case R.id.nav_sair:
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

    public void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);

        if(requestCode == 1){
            if ( resultCode == RESULT_OK )
                finish();
        }
    }
}
