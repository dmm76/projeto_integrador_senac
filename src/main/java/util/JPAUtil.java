package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("tcc_douglas");

    public static EntityManager getEntityManager (){
        return FACTORY.createEntityManager();
    }
}
/* fazer o reload from disk e o mavem sync projetc */