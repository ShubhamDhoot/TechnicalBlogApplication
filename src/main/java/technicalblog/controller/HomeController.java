package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
  @Autowired
  PostService postService;
  @RequestMapping(value = "/")
  public String getAllPosts(Model model){

    List<Post> postArr=postService.getAllPosts();
    model.addAttribute("posts",postArr);
    return "index";
  }

}
