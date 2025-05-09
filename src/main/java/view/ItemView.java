package view;

import dao.ItemDao;
import model.Categoria;
import model.Fornecedor;
import model.Item;
import model.Marca;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ItemView {

    public void cadastrarItem() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);

        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto:");
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitário:"));

            int idMarca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da marca:"));
            int idCategoria = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria:"));
            int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do fornecedor:"));

            Marca marca = em.find(Marca.class, idMarca);
            Categoria categoria = em.find(Categoria.class, idCategoria);
            Fornecedor fornecedor = em.find(Fornecedor.class, idFornecedor);

            if (marca == null || categoria == null || fornecedor == null) {
                JOptionPane.showMessageDialog(null, "Marca, categoria ou fornecedor não encontrados.");
                return;
            }

            Item item = new Item(nome, descricao, valor, marca, categoria, fornecedor);

            em.getTransaction().begin();
            itemDao.cadastrar(item);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public String consultarItens() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);

        List<Item> lista = itemDao.listarTodos();
        StringBuilder sb = new StringBuilder("ID - Nome - Valor - Marca - Categoria - Fornecedor\n");

        for (Item item : lista) {
            sb.append(item.getIdItem()).append(" - ")
                    .append(item.getNomeProduto()).append(" - ")
                    .append(item.getValorUnitarioProduto()).append(" - ")
                    .append(item.getMarca().getDescricao()).append(" - ")
                    .append(item.getCategoria().getDescricao()).append(" - ")
                    .append(item.getFornecedor().getNomeFornecedor()).append("\n");
        }

        em.close();
        return sb.toString();
    }

    public void alterarItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);

        Item item = itemDao.buscarPorId(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item não encontrado!");
            return;
        }

        try {
            String nome = JOptionPane.showInputDialog("Novo nome:", item.getNomeProduto());
            String descricao = JOptionPane.showInputDialog("Nova descrição:", item.getDescricaoProduto());
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Novo valor:", item.getValorUnitarioProduto()));

            int idMarca = Integer.parseInt(JOptionPane.showInputDialog("Novo ID da marca:", item.getMarca().getIdMarca()));
            int idCategoria = Integer.parseInt(JOptionPane.showInputDialog("Novo ID da categoria:", item.getCategoria().getIdCategoria()));
            int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Novo ID do fornecedor:", item.getFornecedor().getIdFornecedor()));

            Marca marca = em.find(Marca.class, idMarca);
            Categoria categoria = em.find(Categoria.class, idCategoria);
            Fornecedor fornecedor = em.find(Fornecedor.class, idFornecedor);

            em.getTransaction().begin();
            item.setNomeProduto(nome);
            item.setDescricaoProduto(descricao);
            item.setValorUnitarioProduto(valor);
            item.setMarca(marca);
            item.setCategoria(categoria);
            item.setFornecedor(fornecedor);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removerItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);

        Item item = itemDao.buscarPorId(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item não encontrado!");
            return;
        }

        em.getTransaction().begin();
        itemDao.remover(item);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
    }
}
