import javax.swing.JOptionPane;
import controller.ClienteController;
import datamodel.Cliente;

import java.util.List;

public class App {
    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver OK");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClienteController controller = new ClienteController();
        String opcao = "";

        while (opcao != null && !opcao.equals("0")) {

            opcao = JOptionPane.showInputDialog(
                    "1. Inserir Cliente\n" +
                            "2. Listar Todos\n" +
                            "3. Deletar Cliente\n" +
                            "4. Alterar Cliente\n" +
                            "5. Buscar Cliente\n" +
                            "0. Sair"
            );

            if (opcao == null) break;

            switch (opcao) {

                case "1":
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String email = JOptionPane.showInputDialog("E-mail:");

                    if (controller.inserir(nome, email)) {
                        JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Dados inválidos.");
                    }
                    break;

                case "2":
                    List<Cliente> lista = controller.listarTodos();

                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
                    } else {
                        StringBuilder sb = new StringBuilder();

                        for (Cliente c : lista) {
                            sb.append(c.getId()).append(" - ")
                                    .append(c.getNome()).append(" - ")
                                    .append(c.getEmail()).append("\n");
                        }

                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;

                case "3":
                    String idStr = JOptionPane.showInputDialog("ID para deletar:");

                    if (idStr == null || idStr.isBlank()) break;

                    try {
                        int id = Integer.parseInt(idStr);

                        if (controller.deletarCliente(id)) {
                            JOptionPane.showMessageDialog(null, "Cliente deletado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "ID não encontrado.");
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Digite um número válido.");
                    }
                    break;

                case "4":
                    String idStrAlt = JOptionPane.showInputDialog("ID do cliente:");

                    if (idStrAlt == null || idStrAlt.isBlank()) break;

                    try {
                        int idAlt = Integer.parseInt(idStrAlt);

                        String novoNome = JOptionPane.showInputDialog("Novo nome:");
                        String novoEmail = JOptionPane.showInputDialog("Novo email:");

                        if (controller.alterar(idAlt, novoNome, novoEmail)) {
                            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao atualizar.");
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID inválido.");
                    }
                    break;

                case "5":
                    String emailBuscar = JOptionPane.showInputDialog("Digite o email:");

                    if (emailBuscar == null || emailBuscar.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Email obrigatório.");
                        break;
                    }

                    Cliente c = controller.buscarPorEmail(emailBuscar);

                    if (c == null) {
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                c.getId() + " - " + c.getNome() + " - " + c.getEmail()
                        );
                    }
                    break;

                case "0":
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}