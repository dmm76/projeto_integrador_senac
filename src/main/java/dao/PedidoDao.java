package dao;
import model.Pedido;

import javax.persistence.EntityManager;
import java.util.List;
public class PedidoDao {
    private EntityManager em;
    public PedidoDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Pedido pedido){
        em.persist(pedido);
    }
    public List<Pedido> buscarTodos(){
        String jpql = "Select p FROM Pedido p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }
    public Pedido buscarPorID(int id){
        return em.find(Pedido.class, id);
    }
    public void remover(Pedido pedido){
        if (!em.contains(pedido)) {
            pedido = em.merge(pedido); // anexa ao contexto de persistência
        }
        em.remove(pedido);
    }
    public void alterar(Pedido pedido){
        em.merge(pedido);
    }
}