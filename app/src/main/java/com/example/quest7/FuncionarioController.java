package com.example.quest7;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private int proxCodigo;
    private final List<Funcionario> funcionarios;

    private static FuncionarioController instancia = null;

    private FuncionarioController(){
        funcionarios = new ArrayList<>();
        proxCodigo = 1;
    }

    public int getProxCodigo(){
        return proxCodigo;
    }

    public static FuncionarioController getInstancia(){
        if (instancia == null)
            instancia = new FuncionarioController();
        return instancia;
    }

    public void cadastrar(Funcionario funcionario){
        funcionario.setCodigo(proxCodigo);
        boolean resultado = funcionarios.add(funcionario);
        if (resultado)
            proxCodigo += 1;

    }

    public boolean alterar(Funcionario funcionario){
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionario.getCodigo()==funcionarios.get(i).getCodigo()){
                funcionarios.set(i, funcionario);
                return true;
            }
        }
        return false;
    }

    public int remover(Funcionario funcionario){
        int cont = 0;
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionario.getCodigo()==funcionarios.get(i).getCodigo()){
                funcionarios.remove(i);
                cont += 1;
            }
        }
        return cont;
    }

    public List<Funcionario> buscarTodos(){
        return new ArrayList<>(funcionarios);
    }

    public Funcionario buscarPorPosicao(int posicao){
        return funcionarios.get(posicao);
    }

    public Funcionario buscarPorcodigo(int codigo){

        for (Funcionario funcionario : funcionarios){
            if (funcionario.getCodigo()==codigo)
                return funcionario;
        }
        return null;
    }

    public List<Funcionario> buscarPorNome(String nome){
        nome = nome.toUpperCase();
        List<Funcionario> funcionarios = new ArrayList<>();
        for (Funcionario funcionario: this.funcionarios) {
            boolean inserir = funcionario.getNome()
                    .toUpperCase()
                    .startsWith(nome);
            if (inserir)
                funcionarios.add(funcionario);
        }
        return funcionarios;
    }

}
