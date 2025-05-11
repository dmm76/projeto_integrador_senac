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

    public boolean cadastrarMarca(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
       MarcaDao marcaDao = new MarcaDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a marca");
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }

        Marca marca = new Marca(descricao);
        em.getTransaction().begin();
        marcaDao.cadastrar(marca);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public String consultarMarca(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        MarcaDao marcaDao = new MarcaDao(em);
        String resultado = "";

        List<Marca> todosRegistros = marcaDao.buscarTodos();

        if (todosRegistros.isEmpty()) {
            em.close();
            return "Nenhuma marca cadastrada.";
        }else{
            int totalRegistros = todosRegistros.size();

            resultado = "ID - DESCRICAO\n";
            for(int i=0; i < totalRegistros; i++){
                resultado += todosRegistros.get(i).getIdMarca() + " - " +
                        todosRegistros.get(i).getDescricao() + "\n";
            }
        }
        em.close();
        return resultado;
    }

    public boolean alterarMarca(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        MarcaDao marcaDao = new MarcaDao(em);

        String descricao = JOptionPane.showInputDialog("Digite a nova descrição da marca:");
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }

        Marca marca = marcaDao.buscarPorID(id);
        if (marca == null) {
            JOptionPane.showMessageDialog(null, "Marca não encontrada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        marca.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean removerMarca(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        MarcaDao marcaDao = new MarcaDao(em);

        Marca marca = marcaDao.buscarPorID(id);
        if (marca == null) {
            JOptionPane.showMessageDialog(null, "Marca não encontrada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        em.remove(marca);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
