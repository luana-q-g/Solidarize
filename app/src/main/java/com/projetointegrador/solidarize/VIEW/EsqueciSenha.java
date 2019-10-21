package com.projetointegrador.solidarize.VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.projetointegrador.solidarize.R;

public class EsqueciSenha extends AppCompatActivity {
    private EditText txt_email;
    private Button btn_enviar;

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        txt_email= (EditText) findViewById(R.id.txt_email);
        btn_enviar= (Button) findViewById(R.id.btn_enviar);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= txt_email.getText().toString();

                Toast.makeText(getApplicationContext(), "Email está sendo enviado...", Toast.LENGTH_LONG).show();

                auth_usuario.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
                            txt_email.setText("");

                            Toast.makeText(getApplicationContext(), "Verifique seu email para trocar sua senha", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                /*auth_usuario.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        //se o email estiver registrado
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Email está sendo enviado...", Toast.LENGTH_LONG).show();

                            auth_usuario.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(), "Verifique seu email para trocar sua senha", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            txt_email.setText("");
                            Toast.makeText(getApplicationContext(), "Insira um email já registrado no sistema!", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/

            }
        });
    }
}
