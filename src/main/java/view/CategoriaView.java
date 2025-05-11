package view;

import dao.CategoriaDao;
import model.Categoria;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class CategoriaView {

    public boolean cadastrarCategoria(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a descrição da categoria");
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }

        Categoria categoria = new Categoria(descricao);
        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public String consultarCategoria(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        String resultado = "";

        List<Categoria> todosRegistros = categoriaDao.buscarTodos();
        if (todosRegistros.isEmpty()) {
            em.close();
            return "Nenhuma categoria cadastrada.";
        }else{
            int totalRegistros = todosRegistros.size();

            resultado = "ID - DESCRICAO\n";
            for(int i=0; i < totalRegistros; i++){
                resultado += todosRegistros.get(i).getIdCategoria() + " - " +
                        todosRegistros.get(i).getDescricao() + "\n";
            }
        }
        return resultado;
    }
    public boolean alterarCategoria(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        Categoria categoria = categoriaDao.buscarPorID(id);
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada.");
            em.close();
            return false;
        }

        String novaDescricao = JOptionPane.showInputDialog("Digite a nova descricao da categoria: ", categoria.getDescricao());
        if (novaDescricao == null || novaDescricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        categoria.setDescricao(novaDescricao);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public boolean removerCategoria(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

       Categoria categoria = categoriaDao.buscarPorID(id);
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
