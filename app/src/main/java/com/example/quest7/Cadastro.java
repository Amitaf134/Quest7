package com.example.quest7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Cadastro extends AppCompatActivity {

    EditText editTextCodigo;
    EditText editTextNome;
    RadioGroup radioGroupGenero;
    EditText editTextCargo;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextCodigo = findViewById(R.id.activity_cadastro_editText_codigo);
        editTextNome = findViewById(R.id.activity_cadastro_editText_Nome);
        radioGroupGenero = findViewById(R.id.radioGroup);
        editTextCargo = findViewById(R.id.activity_cadastro_editText_cargo);

        Intent intent = getIntent();

        if(intent.hasExtra("funcionario")){
            Funcionario funcionario = (Funcionario)intent.getSerializableExtra("funcionario");
        }else {
        }

        btnCadastrar = findViewById(R.id.activity_cadastro_button_cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cadastroFuncionario();
            }
        });
    }

    private void cadastroFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));
        funcionario.setNome(editTextNome.getText().toString());
        char sexo = (radioGroupGenero.getCheckedRadioButtonId() == R.id.activity_cadastro_rbMasculino) ? 'M' : 'F';
        funcionario.setSexo(sexo);
        funcionario.setCargo(editTextCargo.getText().toString());
        FuncionarioController funcionarioController = FuncionarioController.getInstancia();
        funcionarioController.cadastrar(funcionario);
        finish();
    }

}

