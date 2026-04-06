package model;
import controller.Icrud;
import datamodel.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AppDataBase implements Icrud<Cliente> {

    public String url = "jdbc:sqlite:config/banco.db";

    public AppDataBase() {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT NOT NULL, " +
                        "email TEXT NOT NULL)";
                stmt.execute(sql);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    @Override
    public boolean inserir(Cliente obj) {
        String sql = "INSERT INTO clientes(nome, email) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getEmail());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscar(int id) {
        String sql = "SELECT id, nome, email FROM clientes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM clientes";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public boolean alterar(int obj) {
        return false;
    }

    @Override
    public boolean alterar(Cliente obj) {
        String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getEmail());
            pstmt.setInt(3, obj.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao alterar: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscar(String nome, String email) {
        return null;
    }

    public Cliente buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email FROM clientes WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                return c;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar por email: " + e.getMessage());
        }

        return null;
    }
}