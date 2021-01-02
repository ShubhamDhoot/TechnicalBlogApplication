package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  PostService postService;
  @Autowired
  UserService userService;

  @RequestMapping("user/login")
  public String login(){
    return "user/login";
  }
  @RequestMapping("user/registration")
  public String registration(){
    return "user/registration";
  }

  @RequestMapping(value = "user/login", method = RequestMethod.POST)
  public  String userLogin(User user){
      if(userService.login(user)){
        return "redirect:/posts";
      }
      else{
        return "user/login";
      }

  }

  @RequestMapping(value = "user/logout", method = RequestMethod.POST)
  public String userLogout(Model model){

    List<Post> postArr=postService.getAllPosts();
    model.addAttribute("posts",postArr);

    return "index";
  }

  @RequestMapping(value = "user/registration" , method=RequestMethod.POST)
  public String Userregistration(){
    return "user/login";
  }

}
