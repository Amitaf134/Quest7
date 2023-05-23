package com.example.quest7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//minha classe

    Button btCad;
    Button btAtua;
    ListView listViewFuncionario;
    private FuncionarioController funcionarioController;
    private ListaFuncionarioAdapter listaFuncionariosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCad = findViewById(R.id.bt_Cadastrar);
        btAtua = findViewById(R.id.bt_Atualizar);
        listViewFuncionario = findViewById(R.id.listFun);

        btCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCadastro();
            }
        });

        btAtua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLista();
            }
        });
        listViewFuncionario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Funcionario funcionario = funcionarioController.buscarPorPosicao(position);
            funcionarioController.remover(funcionario);
                return true;
            }
        });

        listViewFuncionario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this,Cadastro.class);
                Funcionario funcionario = funcionarioController.buscarPorPosicao(position);
                it.putExtra("funcionario",funcionario);
                startActivity(it);
            }
        });
    }
    private void atualizarLista() {
        funcionarioController = FuncionarioController.getInstancia();
        listaFuncionariosAdapter = new ListaFuncionarioAdapter(MainActivity.this);
        listViewFuncionario.setAdapter(listaFuncionariosAdapter);
    }

    private void iniciarCadastro() {
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);
    }

    protected void onResume(){
        super.onResume();
        atualizarLista();
    }
}