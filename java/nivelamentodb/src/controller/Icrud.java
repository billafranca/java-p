package controller;
import datamodel.Cliente;

import java.util.List;


public interface Icrud<T> {
    boolean inserir(T obj);
    T buscar(int id);
    List<T> listar();
    boolean alterar(int obj);

    boolean alterar(Cliente obj);

    boolean deletar(int id);

    Cliente buscar(String nome, String email);

    Cliente buscarPorEmail(String trim);
}
