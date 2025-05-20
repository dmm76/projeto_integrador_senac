package dao;
import model.PedidoItem;

import javax.persistence.EntityManager;
import java.util.List;
public class PedidoItemDao {
    private EntityManager em;
    public PedidoItemDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(PedidoItem pedidoItem){
        em.persist(pedidoItem);
    }
    public List<PedidoItem> buscarTodos(){
        String jpql = "Select i FROM PedidoItem i";
        return em.createQuery(jpql, PedidoItem.class).getResultList();
    }
    public PedidoItem buscarPorID(int id){
        return em.find(PedidoItem.class, id);
    }
    public List<PedidoItem> buscarPorIdPedido(int idPedido) {
        String jpql = "SELECT pi FROM PedidoItem pi WHERE pi.pedido.idPedido = :idPedido";
        return em.createQuery(jpql, PedidoItem.class)
                .setParameter("idPedido", idPedido)
                .getResultList();
    }
    public void remover(PedidoItem pedidoItem){
        if (!em.contains(pedidoItem)) {
            pedidoItem = em.merge(pedidoItem); // anexa ao contexto de persistência
        }
        em.remove(pedidoItem);
    }
    public void alterar(PedidoItem pedidoItem){
        em.merge(pedidoItem);
    }
}