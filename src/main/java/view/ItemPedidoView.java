package view;

import dao.ItemPedidoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ItemPedidoView {

    public boolean cadastrarItemPedido() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

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

            String valorItemStr = JOptionPane.showInputDialog("Digite o valor unitário do item: ");
            if (valorItemStr == null || valorItemStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            double valorItem = Double.parseDouble(valorItemStr);

            // Buscar objetos
            Pedido pedido = em.find(Pedido.class, idPedido);
            Item item = em.find(Item.class, idItem);

            if (pedido == null || item == null) {
                JOptionPane.showMessageDialog(null, "Item ou Pedido não encontrado.");
                return false;
            }

            //double valorTotalItem = quantidadeItem * valorItem;

            ItemPedido itemPedido = new ItemPedido(item, pedido, quantidadeItem, valorItem);

            em.getTransaction().begin();
            itemPedidoDao.cadastrar(itemPedido);
            em.getTransaction().commit();
            em.refresh(itemPedido);

            JOptionPane.showMessageDialog(null,
                    "Item do pedido cadastrado com sucesso!\n" +
                            "Valor total: R$ " + String.format("%.2f", itemPedido.getValorTotalItem()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item do pedido: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }

    public String consultarItemPedido() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        List<ItemPedido> lista = itemPedidoDao.buscarTodos();
        StringBuilder sb = new StringBuilder("ID - Pedido - Item - Quantidade - Valor Unitário - Valor Total\n");

        for (ItemPedido p : lista) {
            sb.append(p.getIdItemPedido()).append(" - Pedido nº: ")
                    .append(p.getPedido().getIdPedido()).append(" - ")
                    .append(p.getItem().getNomeProduto()).append(" - ")
                    .append(p.getQuantidadeItem()).append(" - ")
                    .append(p.getValorItem()).append(" - ")
                    .append(p.getValorTotalItem()).append("\n");
        }

        em.close();
        return sb.toString();
    }

    public boolean alterarItemPedido(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        ItemPedido itemPedido = itemPedidoDao.buscarPorID(id);
        if (itemPedido == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return false;
        }

        try {
            int idItem = Integer.parseInt(JOptionPane.showInputDialog("Novo ID do Item:", itemPedido.getItem().getIdItem()));
            int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Novo ID do Pedido:", itemPedido.getPedido().getIdPedido()));
            int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Nova quantidade:", itemPedido.getQuantidadeItem()));
            double valorItem = Double.parseDouble(JOptionPane.showInputDialog("Novo valor unitário:", itemPedido.getValorItem()));

            Item item = em.find(Item.class, idItem);
            Pedido pedido = em.find(Pedido.class, idPedido);

            double valorTotalItem = quantidadeItem * valorItem;

            em.getTransaction().begin();
            itemPedido.setItem(item);
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidadeItem(quantidadeItem);
            itemPedido.setValorItem(valorItem);
//            itemPedido.setValorTotalItem(valorTotalItem);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null,
                    "Item pedido atualizado com sucesso!\n" +
                            "Novo total: R$ " + String.format("%.2f", itemPedido.getValorTotalItem()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item pedido: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }

    public boolean removerItemPedido(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);
        ItemPedido itemPedido = itemPedidoDao.buscarPorID(id);
        if (itemPedido == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return false;
        }
        em.getTransaction().begin();
        itemPedidoDao.remover(itemPedido);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}