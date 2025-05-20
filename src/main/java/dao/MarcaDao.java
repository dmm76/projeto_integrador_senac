package dao;
import model.Marca;
import javax.persistence.EntityManager;
import java.util.List;
public class MarcaDao {
    private EntityManager em;
    public MarcaDao(EntityManager em){
        this.em = em;
    }
    public void cadastrar(Marca marca){
        em.persist(marca);
    }
    public List<Marca> buscarTodos(){
        String jpql = "Select m FROM Marca m";
        return em.createQuery(jpql, Marca.class).getResultList();
    }
    public Marca buscarPorID(int id){
        return em.find(Marca.class, id);
    }
    public void remover(Marca marca){
        em.remove(marca);
    }
    public void alterar(Marca marca){
        em.merge(marca);
    }
}