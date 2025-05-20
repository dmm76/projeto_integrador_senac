package dao;
import model.Fornecedor;
import model.Marca;

import javax.persistence.EntityManager;
import java.util.List;
public class FornecedorDao {
    private EntityManager em;
    public FornecedorDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Fornecedor fornecedor){
        em.persist(fornecedor);
    }
    public List<Fornecedor> buscarTodos(){
        String jpql = "Select f FROM Fornecedor f";
        return em.createQuery(jpql, Fornecedor.class).getResultList();
    }
    public Fornecedor buscarPorID(int id){
        return em.find(Fornecedor.class, id);
    }
    public void remover(Fornecedor fornecedor){
        em.remove(fornecedor);
    }
    public void alterar(Fornecedor fornecedor){
        em.merge(fornecedor);
    }
}