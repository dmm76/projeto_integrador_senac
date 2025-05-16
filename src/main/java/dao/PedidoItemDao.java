package dao;

import model.PedidoItem;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoItemDao {
    private EntityManager em;

    //construtor do PedidoDao
    public PedidoItemDao(EntityManager em){
        this.em = em;
    }

    //cadastrar
    public void cadastrar(PedidoItem pedidoItem){
        em.persist(pedidoItem);
    }
    //listar
    public List<PedidoItem> buscarTodos(){
        String jpql = "Select i FROM PedidoItem i";
        return em.createQuery(jpql, PedidoItem.class).getResultList();
    }
    //buscar por Id
    public PedidoItem buscarPorID(int id){
        return em.find(PedidoItem.class, id);
    }
    //remover
    public void remover(PedidoItem pedidoItem){
        if (!em.contains(pedidoItem)) {
            pedidoItem = em.merge(pedidoItem); // anexa ao contexto de persistência
        }
        em.remove(pedidoItem);
    }
    //alterar
    public void alterar(PedidoItem pedidoItem){
        em.merge(pedidoItem);
    }
}
