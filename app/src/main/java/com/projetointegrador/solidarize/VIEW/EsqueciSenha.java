package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.projetointegrador.solidarize.R;

public class EsqueciSenha extends AppCompatActivity {
    private EditText txt_email;
    private Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        txt_email= (EditText) findViewById(R.id.txt_email);
        btn_enviar= (Button) findViewById(R.id.btn_enviar);
    }
}
