package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.util.List;

@Repository
public class PostRepository {

  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory emf;

  public List<Post> getAllPost(){
    EntityManager em=emf.createEntityManager();
    TypedQuery<Post> query= em.createQuery("Select p from Post p",Post.class);
    List<Post> postArr=query.getResultList();
    return postArr;
  }

  public Post getPost(Integer id){
    EntityManager em=emf.createEntityManager();
    Post p=em.find(Post.class,id);
    return p;
  }

  public Post createPost(Post newPost){

    EntityManager em=emf.createEntityManager();
    EntityTransaction transaction=em.getTransaction();

    try{
      transaction.begin();
      em.persist(newPost);
      transaction.commit();
    }
    catch (Exception e){
      transaction.rollback();
    }


    return newPost;
  }

  public void updatePost(Post p){

    EntityManager em=emf.createEntityManager();
    EntityTransaction transaction=em.getTransaction();

    try {
      transaction.begin();
      em.merge(p);
      transaction.commit();
    }catch(Exception e) {
      transaction.rollback();
    }

  }

  public void deletePost(Integer id){
    EntityManager em=emf.createEntityManager();
    EntityTransaction transaction=em.getTransaction();

    try {
      transaction.begin();
      Post p=em.find(Post.class,id);
      em.remove(p);
      transaction.commit();
    }catch(Exception e) {
      transaction.rollback();
    }
  }

}
