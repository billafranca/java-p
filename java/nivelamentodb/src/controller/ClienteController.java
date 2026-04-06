package controller;

import datamodel.Cliente;
import model.AppDataBase;

import java.util.List;

public class ClienteController {

    private final AppDataBase db = new AppDataBase();

    public boolean inserir(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            return false;
        }

        Cliente c = new Cliente();
        c.setNome(nome.trim());
        c.setEmail(email.trim());

        return db.inserir(c);
    }

    public boolean deletarCliente(int id) {
        if (id <= 0) return false;
        return db.deletar(id);
    }

    public List<Cliente> listarTodos() {
        return db.listar();
    }

    public Cliente buscarPorEmail(String email) {
        if (email == null || email.isBlank()) return null;
        return db.buscarPorEmail(email.trim());
    }

    public boolean alterar(int id, String nome, String email) {
        if (id <= 0 || nome == null || nome.isBlank() || email == null || email.isBlank()) {
            return false;
        }

        Cliente c = new Cliente();
        c.setId(id);
        c.setNome(nome.trim());
        c.setEmail(email.trim());

        return db.alterar(c);
    }
}