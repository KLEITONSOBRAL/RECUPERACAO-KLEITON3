package com.mycompany.sistema.de.pedidos.de.restaurante;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class SistemaPedidosRestaurante {
    public static void main(String[] args) {
        try {
            
            String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

            
            int quantidadeItens = Integer.parseInt(JOptionPane.showInputDialog("Quantos itens deseja pedir?"));

            
            String[] nomesItens = new String[quantidadeItens];
            int[] quantidadesItens = new int[quantidadeItens];
            double[] precosItens = new double[quantidadeItens];

            
            for (int i = 0; i < quantidadeItens; i++) {
                nomesItens[i] = JOptionPane.showInputDialog("Digite o nome do item " + (i + 1) + ":");
                quantidadesItens[i] = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do item " + (i + 1) + ":"));
                precosItens[i] = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do item " + (i + 1) + ":"));
            }

            
            double valorTotal = 0;
            StringBuilder resumoPedido = new StringBuilder();
            resumoPedido.append("Nome do Cliente: ").append(nomeCliente).append("\n\nItens do Pedido:\n");

            for (int i = 0; i < quantidadeItens; i++) {
                double valorItem = quantidadesItens[i] * precosItens[i];
                resumoPedido.append("Item: ").append(nomesItens[i])
                             .append(", Quantidade: ").append(quantidadesItens[i])
                             .append(", Preço Unitário: R$ ").append(precosItens[i])
                             .append(", Total: R$ ").append(valorItem).append("\n");
                valorTotal += valorItem;
            }

            resumoPedido.append("\nValor Total do Pedido: R$ ").append(valorTotal);

            
            JOptionPane.showMessageDialog(null, resumoPedido.toString(), "Resumo do Pedido", JOptionPane.INFORMATION_MESSAGE);

            
            try (FileWriter writer = new FileWriter("pedido_" + nomeCliente + ".txt")) {
                writer.write(resumoPedido.toString());
            }

            JOptionPane.showMessageDialog(null, "Pedido salvo com sucesso!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o pedido: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro de entrada: por favor, insira números válidos para quantidade e preço.");
        }
    }
}
