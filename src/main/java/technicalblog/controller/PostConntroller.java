package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Category;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostConntroller {

  @Autowired
  PostService postService;

  @RequestMapping("posts")
  public  String getUserPost(Model model){
    List<Post> posts=postService.getAllPosts();
    model.addAttribute("posts",posts);

    return "Post";
  }

  @RequestMapping("/posts/newpost")
  public String newPost() {
    return "posts/create";
  }

  @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
  public String createPost(Post newPost, HttpSession session){
    User user = (User)session.getAttribute("loggeduser");
    newPost.setUser(user);

    if(newPost.getSpringBlog()!=null){
      Category springCategory=new Category();
      springCategory.setCategory(newPost.getSpringBlog());
      newPost.getCategories().add(springCategory);
    }

    if(newPost.getJavaBlog()!=null){
      Category springCategory=new Category();
      springCategory.setCategory(newPost.getJavaBlog());
      newPost.getCategories().add(springCategory);
    }

    postService.createPost(newPost);
    return "redirect:/posts";
  }

  @RequestMapping(value = "/editPost", method = RequestMethod.GET)
  public String editPost(@RequestParam(name="postId") Integer postId, Model model) {
    Post post = postService.getPost(postId);
    model.addAttribute("post",post);
    return "posts/edit";
  }

  @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
  public String editPostSubmit(@RequestParam(name="postId") Integer postId, Post updatedPost, HttpSession session) {
    User user = (User)session.getAttribute("loggeduser");
    updatedPost.setUser(user);
   updatedPost.setId(postId);

    if(updatedPost.getSpringBlog()!=null){
      Category springCategory=new Category();
      springCategory.setCategory(updatedPost.getSpringBlog());
      updatedPost.getCategories().add(springCategory);
    }

    if(updatedPost.getJavaBlog()!=null){
      Category springCategory=new Category();
      springCategory.setCategory(updatedPost.getJavaBlog());
      updatedPost.getCategories().add(springCategory);
    }

   postService.updatePost(updatedPost);
   return "redirect:/posts";
  }

  public String deletePost(@RequestParam(name="postId") Integer postId) {

    postService.deletePost(postId);
    return "redirect:/posts";
  }


}
