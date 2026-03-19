package controller;

import datamodel.Cliente;
import model.AppDataBase;

import java.util.List;

public class ClienteController {

    private AppDataBase db = new AppDataBase();

    public boolean cadastrarCliente(String nome, String email) {
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        return db.inserir(c);
    }

    public boolean deletarCliente(int id) {
        return db.deletar(id);
    }

    public List<Cliente> listarTodos() {
        return db.listar();
    }
}