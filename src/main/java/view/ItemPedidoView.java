package view;

import dao.ItemPedidoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ItemPedidoView {

    public void cadastrarItemPedido() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        try {
            int idItem = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Item: "));
            int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Pedido: "));
            int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de itens: "));
            double valorItem = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitário do item: "));

            // Buscar objetos
            Pedido pedido = em.find(Pedido.class, idPedido);
            Item item = em.find(Item.class, idItem);

            if (pedido == null || item == null) {
                JOptionPane.showMessageDialog(null, "Item ou Pedido não encontrado.");
                return;
            }

            double valorTotalItem = quantidadeItem * valorItem;

            ItemPedido itemPedido = new ItemPedido(item, pedido, quantidadeItem, valorItem, valorTotalItem);

            em.getTransaction().begin();
            itemPedidoDao.cadastrar(itemPedido);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Item do pedido cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item do pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public String consultarItemPedido() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        List<ItemPedido> lista = itemPedidoDao.buscarTodos();
        StringBuilder sb = new StringBuilder("ID - Item - Pedido - Quantidade - Valor Unitário - Valor Total\n");

        for (ItemPedido p : lista) {
            sb.append(p.getIdItemPedido()).append(" - ")
                    .append(p.getItem().getNomeProduto()).append(" - Pedido #")
                    .append(p.getPedido().getIdPedido()).append(" - ")
                    .append(p.getQuantidadeItem()).append(" - ")
                    .append(p.getValorItem()).append(" - ")
                    .append(p.getValorTotalItem()).append("\n");
        }

        em.close();
        return sb.toString();
    }

    public void alterarItemPedido(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        ItemPedido itemPedido = itemPedidoDao.buscarPorID(id);
        if (itemPedido == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return;
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
            itemPedido.setValorTotalItem(valorTotalItem);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Item pedido atualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removerPedido(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

        ItemPedido itemPedido = itemPedidoDao.buscarPorID(id);
        if (itemPedido == null) {
            JOptionPane.showMessageDialog(null, "Item pedido não encontrado!");
            return;
        }

        em.getTransaction().begin();
        itemPedidoDao.remover(itemPedido);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Item pedido removido com sucesso!");
    }
}