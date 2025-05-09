package view;

import dao.CategoriaDao;
import model.Categoria;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class CategoriaView {

    public void cadastrarCategoria(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a categoria");

        Categoria categoria = new Categoria(descricao);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria);

        em.getTransaction().commit();

        em.close();
    }
    public String consultarCategoria(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(em);

        List<Categoria> todosRegistros = categoriaDao.buscarTodos();
        int totalRegistros = todosRegistros.size();

        String resultado = "ID - DESCRICAO\n";
        for(int i=0; i < totalRegistros; i++){
            resultado += todosRegistros.get(i).getIdCategoria() + " - " +
                    todosRegistros.get(i).getDescricao() + "\n";
        }
        return resultado;
    }
    public void alterarCategoria(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

        String descricao = JOptionPane.showInputDialog("Digite a descricao: ");

        Categoria categoria = categoriaDao.buscarPorID(id);

        em.getTransaction().begin();
        categoria.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
    }
    public void removerCategoria(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);

       Categoria categoria = categoriaDao.buscarPorID(id);

        em.getTransaction().begin();
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();
    }
}
