package view;

import dao.PedidoItemDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class PedidoItemView {
    public boolean cadastrarPedidoItem() {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoItemDao pedidoItemDao = new PedidoItemDao(em);
        String resultado = null;
        try {
            ItemView itemView = new ItemView();
            resultado = itemView.consultarItens();
            String idItemStr = JOptionPane.showInputDialog(null, resultado, "Digite o ID do Item: ");
            if (idItemStr == null || idItemStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idItem = Integer.parseInt(idItemStr);
            PedidoView pedidoView = new PedidoView();
            resultado = pedidoView.consultarPedido();
            String idPedidoStr = JOptionPane.showInputDialog(null, resultado, "Digite o ID do Pedido: ");
            if (idPedidoStr == null || idPedidoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idPedido = Integer.parseInt(idPedidoStr);
            String quantidadeItemStr = JOptionPane.showInputDialog("Digite a quantidade de itens: ");
            if (quantidadeItemStr == null || quantidadeItemStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int quantidadeItem = Integer.parseInt(quantidadeItemStr);

            Pedido pedido = em.find(Pedido.class, idPedido);
            Item item = em.find(Item.class, idItem);

            if (pedido == null || item == null) {
                JOptionPane.showMessageDialog(null, "Item ou Pedido não encontrado.");
                return false;
            }
            String valorItemStr = JOptionPane.showInputDialog("Digite o valor unitário do item: ", item.getValorUnitarioProduto());
            if (valorItemStr == null || valorItemStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            double valorItem = Double.parseDouble(valorItemStr);
            PedidoItem pedidoItem = new PedidoItem(item, pedido, quantidadeItem, valorItem);
            em.getTransaction().begin();
            pedidoItemDao.cadastrar(pedidoItem);
            em.getTransaction().commit();
            em.refresh(pedidoItem);
            JOptionPane.showMessageDialog(null,
                    "Item do pedido cadastrado com sucesso!\n" +
                            "Valor total: R$ " + String.format("%.2f", pedidoItem.getValorTotalItem()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item do pedido: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }
    public String consultarPedidoItem() {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoItemDao pedidoItemDao = new PedidoItemDao(em);
        List<PedidoItem> lista = pedidoItemDao.buscarTodos();
        StringBuilder sb = new StringBuilder("ID - Pedido - Item - Quantidade - Valor Unitário - Valor Total\n");
        for (PedidoItem p : lista) {
            sb.append(p.getIdPedidoItem()).append(" - Pedido nº: ")
                    .append(p.getPedido().getIdPedido()).append(" - ")
                    .append(p.getItem().getNomeProduto()).append(" - ")
                    .append(p.getQuantidadeItem()).append(" - ")
                    .append(p.getValorItem()).append(" - ")
                    .append(p.getValorTotalItem()).append("\n");
        }
        em.close();
        return sb.toString();
    }
    public boolean alterarPedidoItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoItemDao pedidoItemDao = new PedidoItemDao(em);
        PedidoItem pedidoItem = pedidoItemDao.buscarPorID(id);
        if (pedidoItem == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return false;
        }
        try {
            int idItem = Integer.parseInt(JOptionPane.showInputDialog("Novo ID do Item:", pedidoItem.getItem().getIdItem()));
            int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Novo ID do Pedido:", pedidoItem.getPedido().getIdPedido()));
            int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Nova quantidade:", pedidoItem.getQuantidadeItem()));
            double valorItem = Double.parseDouble(JOptionPane.showInputDialog("Novo valor unitário:", pedidoItem.getValorItem()));
            Item item = em.find(Item.class, idItem);
            Pedido pedido = em.find(Pedido.class, idPedido);
            double valorTotalItem = quantidadeItem * valorItem;
            em.getTransaction().begin();
            pedidoItem.setItem(item);
            pedidoItem.setPedido(pedido);
            pedidoItem.setQuantidadeItem(quantidadeItem);
            pedidoItem.setValorItem(valorItem);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,
                    "Item pedido atualizado com sucesso!\n" +
                            "Novo total: R$ " + String.format("%.2f", pedidoItem.getValorTotalItem()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item pedido: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }
    public boolean removerPedidoItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoItemDao pedidoItemDao = new PedidoItemDao(em);
        PedidoItem pedidoItem = pedidoItemDao.buscarPorID(id);
        if (pedidoItem == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return false;
        }
        em.getTransaction().begin();
        pedidoItemDao.remover(pedidoItem);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public String consultarPedidoItemPorPedido(int idPedido) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoItemDao pedidoItemDao = new PedidoItemDao(em);

        List<PedidoItem> lista = pedidoItemDao.buscarPorIdPedido(idPedido);
        StringBuilder sb = new StringBuilder("Itens do Pedido nº: " + idPedido + "\n");
        sb.append("ID - Item - Quantidade - Valor Unitário - Valor Total\n");
        double valorRetorno = 0.0;
        for (PedidoItem p : lista) {
            valorRetorno += p.getValorTotalItem();
            sb.append(p.getIdPedidoItem()).append(" - ")
                    .append(p.getItem().getNomeProduto()).append(" - ")
                    .append(p.getQuantidadeItem()).append(" - ")
                    .append(String.format("R$ %.2f", p.getValorItem())).append(" - ")
                    .append(String.format("R$ %.2f", p.getValorTotalItem())).append("\n");
        }
        if (lista.isEmpty()) {
            sb.append("Nenhum item encontrado para esse pedido.");
        }
        em.close();
        sb.append("\nValor Total do Pedido: ").append(String.format("R$ %.2f", valorRetorno)).append("\n\n");
        return sb.toString();
    }
    public String consultarPedidoItemComFiltro() {
        int escolha = JOptionPane.showConfirmDialog(
                null,
                "Deseja buscar os itens por ID do Pedido?",
                "Consulta de Itens",
                JOptionPane.YES_NO_CANCEL_OPTION
        );

        if (escolha == JOptionPane.CANCEL_OPTION || escolha == JOptionPane.CLOSED_OPTION) {
            return "Consulta cancelada.";
        }

        if (escolha == JOptionPane.YES_OPTION) {
            String idPedidoStr = JOptionPane.showInputDialog("Digite o ID do Pedido:");
            if (idPedidoStr == null || idPedidoStr.trim().isEmpty()) {
                return "ID não informado. Consulta cancelada.";
            }

            try {
                int idPedido = Integer.parseInt(idPedidoStr);
                return consultarPedidoItemPorPedido(idPedido);
            } catch (NumberFormatException e) {
                return "ID inválido. Digite um número inteiro.";
            }
        }
        return consultarPedidoItem();
    }
}