package controller;
import java.util.List;

// Use apenas uma interface genérica <T>
public interface Icrud<T> {
    boolean inserir(T obj);
    T buscar(int id);
    List<T> listar();
    boolean alterar(T obj);
    boolean deletar(int id);
}
