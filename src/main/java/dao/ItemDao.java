package dao;
import model.Item;

import javax.persistence.EntityManager;
import java.util.List;
public class ItemDao {
    private EntityManager em;
    public ItemDao(EntityManager em) {
        this.em = em;
    }
    public void cadastrar(Item item) {
        em.persist(item);
    }
    public Item buscarPorId(int id) {
        return em.find(Item.class, id);
    }
    public List<Item> listarTodos() {
        String jpql = "SELECT i FROM Item i";
        return em.createQuery(jpql, Item.class).getResultList();
    }
    public void atualizar(Item item) {
        em.merge(item);
    }
    public void remover(Item item) {
        if (!em.contains(item)) {
            item = em.merge(item);
        }
        em.remove(item);
    }
}