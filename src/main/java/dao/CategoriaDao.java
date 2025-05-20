package dao;
import model.Categoria;
import javax.persistence.EntityManager;
import java.util.List;
public class CategoriaDao {
    private EntityManager em;
    public CategoriaDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Categoria categoria){
        em.persist(categoria);
    }
    public List<Categoria> buscarTodos(){
        String jpql = "Select c FROM Categoria c";
        return em.createQuery(jpql, Categoria.class).getResultList();
    }
    public Categoria buscarPorID(int id){
        return em.find(Categoria.class, id);
    }
    public void remover(Categoria categoria){
        em.remove(categoria);
    }
    public void alterar(Categoria categoria){
        em.merge(categoria);
    }
}