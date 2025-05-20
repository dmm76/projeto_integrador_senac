package dao;
import model.Cliente;
import model.Marca;

import javax.persistence.EntityManager;
import java.util.List;
public class ClienteDao {
    private EntityManager em;
    public ClienteDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    }
    public List<Cliente> buscarTodos(){
        String jpql = "Select c FROM Cliente c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }
    public Cliente buscarPorID(int id){
        return em.find(Cliente.class, id);
    }
    public void remover(Cliente cliente){
        em.remove(cliente);
    }
    public void alterar(Cliente cliente){
        em.merge(cliente);
    }
}