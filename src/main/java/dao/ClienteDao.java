package dao;

import model.Cliente;
import model.Marca;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    //construtor do FormaPagamentoDao
    public ClienteDao(EntityManager em){
        this.em = em;
    }

    //cadastrar
    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    }
    //listar
    public List<Cliente> buscarTodos(){
        String jpql = "Select c FROM Cliente c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }
    //buscar por Id
    public Cliente buscarPorID(int id){
        return em.find(Cliente.class, id);
    }
    //remover
    public void remover(Cliente cliente){
        em.remove(cliente);
    }
    //alterar
    public void alterar(Cliente cliente){
        em.merge(cliente);
    }

}
