package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.model.UserProfile;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import javax.servlet.http.HttpSession;
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
    return "/user/login";
  }

  @RequestMapping("user/registration")
  public String registration(Model model){
    User user=new User();
    UserProfile userProfile=new UserProfile();
    user.setProfile(userProfile);
    model.addAttribute("User",user);
    return "user/registration";
  }

  @RequestMapping(value = "user/login", method = RequestMethod.POST)
  public  String userLogin(User user, HttpSession session){
      User existingUser=userService.login(user);
      if(existingUser!=null){
        session.setAttribute("loggeduser",existingUser);
        return "redirect:/posts";
      }
      else{
        System.out.println("in else");
        return "/user/login";
      }

  }

  @RequestMapping(value = "user/logout", method = RequestMethod.POST)
  public String userLogout(Model model, HttpSession session){
    session.invalidate();
    List<Post> postArr=postService.getAllPosts();
    model.addAttribute("posts",postArr);

    return "index";
  }

  @RequestMapping(value = "user/registration" , method=RequestMethod.POST)
  public String Userregistration(User user){
    userService.registerUser(user);
    return "user/login";
  }

}
