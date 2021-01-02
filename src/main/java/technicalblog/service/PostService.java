package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

  @Autowired
  PostRepository postRepository;
  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory emf;
  public List<Post> getAllPosts(){

    return postRepository.getAllPost();
  }

  public Post getPost(Integer id){
    Post postArr=new Post();
    //postArr.add(postRepository.getPost(id));
    return postRepository.getPost(id);
  }

  public void createPost(Post newPost){
    newPost.setDate(new Date());
    postRepository.createPost(newPost);
  }

  public void updatePost(Post p){
    p.setDate(new Date());
    postRepository.updatePost(p);
  }

  public void deletePost(Integer id){
    postRepository.deletePost(id);
  }

}
