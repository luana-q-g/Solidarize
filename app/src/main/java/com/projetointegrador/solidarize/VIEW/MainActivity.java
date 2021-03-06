package com.projetointegrador.solidarize.VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.Usuario;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class MainActivity extends AppCompatActivity {
    private EditText txt_email;
    private EditText txt_senha;
    private TextView lbl_esqueci_senha;
    private Button btn_entrar;
    private TextView lbl_registrar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();
    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email= (EditText) findViewById(R.id.txt_email);
        txt_senha= (EditText) findViewById(R.id.txt_senha);
        lbl_esqueci_senha= (TextView) findViewById(R.id.lbl_esqueci_senha);
        lbl_registrar= (TextView) findViewById(R.id.lbl_registrar);
        btn_entrar= (Button) findViewById(R.id.btn_entrar);

        if(auth_usuario.getCurrentUser() != null){
            Intent i_nav_draw= new Intent(getApplicationContext(), NavDrawMenu.class);
            startActivity(i_nav_draw);
        }

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verificacao dos dados email e senha
                String email, senha;
                email= txt_email.getText().toString();
                senha= txt_senha.getText().toString();

                auth_usuario.signInWithEmailAndPassword(email, senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Testa se logou com sucesso:
                        if ( task.isSuccessful() ) {
                            //esvazia campos de login
                            txt_email.setText("");
                            txt_senha.setText("");

                            Intent i_nav_draw= new Intent(getApplicationContext(), NavDrawMenu.class);
                            startActivity(i_nav_draw);
                        }
                        else {
                            // Exibe a mensagem de erro do Firebase num Toast:
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            //String error_code= e.getErrorCode();
                            Toast.makeText(getApplicationContext(), "ERRO: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        lbl_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vai para SelecionaTipoCadastro
                Intent i_seleciona_tipo= new Intent(getApplicationContext(), SelecionaTipoCadastro.class);
                startActivity(i_seleciona_tipo);
            }
        });

        lbl_esqueci_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vai para EsqueciSenha
                Intent i_esqueci_senha= new Intent(getApplicationContext(), EsqueciSenha.class);
                startActivity(i_esqueci_senha);
            }
        });
    }
}
