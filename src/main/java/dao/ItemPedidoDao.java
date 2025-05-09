package dao;

import model.ItemPedido;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemPedidoDao {
    private EntityManager em;

    //construtor do PedidoDao
    public ItemPedidoDao(EntityManager em){
        this.em = em;
    }

    //cadastrar
    public void cadastrar(ItemPedido itemPedido){
        em.persist(itemPedido);
    }
    //listar
    public List<ItemPedido> buscarTodos(){
        String jpql = "Select i FROM ItemPedido i";
        return em.createQuery(jpql, ItemPedido.class).getResultList();
    }
    //buscar por Id
    public ItemPedido buscarPorID(int id){
        return em.find(ItemPedido.class, id);
    }
    //remover
    public void remover(ItemPedido itemPedido){
        if (!em.contains(itemPedido)) {
            itemPedido = em.merge(itemPedido); // anexa ao contexto de persistência
        }
        em.remove(itemPedido);
    }
    //alterar
    public void alterar(ItemPedido itemPedido){
        em.merge(itemPedido);
    }
}
