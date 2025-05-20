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
    public boolean cadastrarItem() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto:");
            if (descricao == null || descricao.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            String inputValor = JOptionPane.showInputDialog("Digite o valor unitário:");
            if (inputValor == null || inputValor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            double valor = Double.parseDouble(inputValor);
            MarcaView marcaView = new MarcaView();
            JOptionPane.showMessageDialog(null, marcaView.consultarMarca());
            int idMarca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da marca:"));
            CategoriaView categoriaView = new CategoriaView();
            JOptionPane.showMessageDialog(null, categoriaView.consultarCategoria());
            int idCategoria = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria:"));
            FornecedorView fornecedorView = new FornecedorView();
            JOptionPane.showMessageDialog(null, fornecedorView.consultarFornecedor());
            int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do fornecedor:"));
            Marca marca = em.find(Marca.class, idMarca);
            Categoria categoria = em.find(Categoria.class, idCategoria);
            Fornecedor fornecedor = em.find(Fornecedor.class, idFornecedor);
            if (marca == null || categoria == null || fornecedor == null) {
                JOptionPane.showMessageDialog(null, "Marca, categoria ou fornecedor não encontrados.");
                return false;
            }
            Item item = new Item(nome, descricao, valor, marca, categoria, fornecedor);
            em.getTransaction().begin();
            itemDao.cadastrar(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
    public String consultarItens() {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);
        List<Item> lista = itemDao.listarTodos();
        if (lista.isEmpty()) {
            em.close();
            return "Nenhum item cadastrado.";
        }
        StringBuilder sb = new StringBuilder("ID - Nome - Valor - Marca - Categoria - Fornecedor\n");
        for (Item item : lista) {
            sb.append(item.getIdItem()).append(" - ")
                    .append(item.getNomeProduto()).append(" - R$ ")
                    .append(String.format("%.2f", item.getValorUnitarioProduto())).append(" - ")
                    .append(item.getMarca().getDescricao()).append(" - ")
                    .append(item.getCategoria().getDescricao()).append(" - ")
                    .append(item.getFornecedor().getNomeFornecedor()).append("\n");
        }
        em.close();
        return sb.toString();
    }
    public boolean alterarItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);

        Item item = itemDao.buscarPorId(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item não encontrado!");
            return false;
        }
        try {
            String nome = JOptionPane.showInputDialog("Novo nome:", item.getNomeProduto());
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alteração cancelada.");
                em.close();
                return false;
            }
            String descricao = JOptionPane.showInputDialog("Nova descrição:", item.getDescricaoProduto());
            if (descricao == null || descricao.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alteração cancelada.");
                em.close();
                return false;
            }
            String valorStr = JOptionPane.showInputDialog("Novo valor:", item.getValorUnitarioProduto());
            if (valorStr == null || valorStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alteração cancelada.");
                em.close();
                return false;
            }
            double valor = Double.parseDouble(valorStr);
            MarcaView marcaView = new MarcaView();
            JOptionPane.showMessageDialog(null, marcaView.consultarMarca());
            int idMarca = Integer.parseInt(JOptionPane.showInputDialog("Novo ID da marca:", item.getMarca().getIdMarca()));
            CategoriaView categoriaView = new CategoriaView();
            JOptionPane.showMessageDialog(null, categoriaView.consultarCategoria());
            int idCategoria = Integer.parseInt(JOptionPane.showInputDialog("Novo ID da categoria:", item.getCategoria().getIdCategoria()));
            FornecedorView fornecedorView = new FornecedorView();
            JOptionPane.showMessageDialog(null, fornecedorView.consultarFornecedor());
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
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
    public boolean removerItem(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ItemDao itemDao = new ItemDao(em);
        Item item = itemDao.buscarPorId(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item não encontrado!");
            em.close();
            return false;
        }
        em.getTransaction().begin();
        itemDao.remover(item);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}