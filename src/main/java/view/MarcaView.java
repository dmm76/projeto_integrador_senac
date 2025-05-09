package view;

import dao.CategoriaDao;
import dao.MarcaDao;
import model.Categoria;
import model.Marca;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class MarcaView {
    public void cadastrarMarca(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
       MarcaDao marcaDao = new MarcaDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a marca");

        Marca marca = new Marca(descricao);

        em.getTransaction().begin();

        marcaDao.cadastrar(marca);

        em.getTransaction().commit();

        em.close();
    }
    public String consultarMarca(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();

        MarcaDao marcaDao = new MarcaDao(em);

        List<Marca> todosRegistros = marcaDao.buscarTodos();
        int totalRegistros = todosRegistros.size();

        String resultado = "ID - DESCRICAO\n";
        for(int i=0; i < totalRegistros; i++){
            resultado += todosRegistros.get(i).getIdMarca() + " - " +
                    todosRegistros.get(i).getDescricao() + "\n";
        }
        return resultado;
    }
    public void alterarMarca(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        MarcaDao marcaDao = new MarcaDao(em);

        String descricao = JOptionPane.showInputDialog("Digite a marca: ");

        Marca marca = marcaDao.buscarPorID(id);

        em.getTransaction().begin();
        marca.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
    }
    public void removerMarca(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        MarcaDao marcaDao = new MarcaDao(em);

        Marca marca = marcaDao.buscarPorID(id);

        em.getTransaction().begin();
        em.remove(marca);
        em.getTransaction().commit();
        em.close();
    }
}
