package view;

import javax.swing.JOptionPane;
import controller.ClienteController;
import datamodel.Cliente;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ClienteController controller = new ClienteController();
        String opcao = "";

        while (opcao != null && !opcao.equals("0")) {
            opcao = JOptionPane.showInputDialog(
                    "1. Inserir Cliente\n" +
                            "2. Listar Todos\n" +
                            "3. Deletar Cliente\n" +
                            "0. Sair"
            );

            if (opcao == null) break;

            switch (opcao) {
                case "1":
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String email = JOptionPane.showInputDialog("E-mail:");
                    controller.cadastrarCliente(nome, email);
                    break;

                case "2":
                    List<Cliente> lista = controller.listarTodos();
                    StringBuilder sb = new StringBuilder();
                    for (Cliente c : lista) {
                        sb.append(c.getId()).append(" - ").append(c.getNome()).append(" - ").append(c.getEmail()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;

                case "3":
                    String idStr = JOptionPane.showInputDialog("ID para deletar:");
                    if (idStr != null && !idStr.isEmpty()) {
                        int id = Integer.parseInt(idStr);
                        controller.deletarCliente(id);
                    }
                    break;

                case "0":
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
            }
        }
    }
}