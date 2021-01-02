package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {

  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory emf;

  public void registerUser(User user){

    EntityManager em=emf.createEntityManager();
    EntityTransaction transaction=em.getTransaction();

    try{
      transaction.begin();
      em.persist(user);
      transaction.commit();
    }
    catch (Exception e){
      transaction.rollback();
    }

  }

  public User checkUser(String username, String password){

    try {
      EntityManager em = emf.createEntityManager();
      TypedQuery<User> typeQuery = em.createQuery("Select u From User u where u.username= :username AND u.password= :password", User.class);
      typeQuery.setParameter("username", username);
      typeQuery.setParameter("password", password);

      return typeQuery.getSingleResult();
    }
    catch(NoResultException nre) {
      return null;
    }
    
  }
}
