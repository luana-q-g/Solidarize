package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.DAO.PessoaDAO;
import com.projetointegrador.solidarize.R;

public class MainActivity extends AppCompatActivity {
    private EditText txt_email;
    private EditText txt_senha;
    private TextView lbl_esqueci_senha;
    private Button btn_entrar;
    private TextView lbl_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email= (EditText) findViewById(R.id.txt_email);
        txt_senha= (EditText) findViewById(R.id.txt_senha);
        lbl_esqueci_senha= (TextView) findViewById(R.id.lbl_esqueci_senha);
        lbl_registrar= (TextView) findViewById(R.id.lbl_registrar);

        Pessoa user= new Pessoa("lulu@gmail.com", "luana", "9809809", "298929293", "28/11/2002", "SBC", "SP");
        PessoaDAO p= new PessoaDAO();
        p.inserirUsuarioPessoa(user);
    }
}
