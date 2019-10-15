package com.projetointegrador.solidarize.VIEW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.projetointegrador.solidarize.R;

public class SelecionaTipoCadastro extends AppCompatActivity {
    private RadioGroup rad_group;
    private RadioButton rad_institu;
    private RadioButton rad_pessoa;
    private Button btn_continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_tipo_cadastro);

        rad_group= (RadioGroup) findViewById(R.id.rad_group_escolha);
        rad_institu= (RadioButton) findViewById(R.id.rad_instituicao);
        rad_pessoa= (RadioButton) findViewById(R.id.rad_pessoa);
        btn_continuar= (Button) findViewById(R.id.btn_continuar_escolha_tipo_cadastro);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_rad_group= rad_group.getCheckedRadioButtonId();

                if(id_rad_group == -1){
                    Toast.makeText(getApplicationContext(), "Selecione uma opção de usuário!", Toast.LENGTH_LONG);
                }
                else{
                    if(id_rad_group == R.id.rad_instituicao){
                        Intent i_inst= new Intent(getApplicationContext(), CadastroInstituicao.class);
                        startActivity(i_inst);
                    }
                    else{
                        Intent i_pesso= new Intent(getApplicationContext(), CadastroPessoa.class);
                        startActivity(i_pesso);
                    }
                }
            }
        });
    }
}
