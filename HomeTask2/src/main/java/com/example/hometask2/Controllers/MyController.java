package com.example.hometask2.Controllers;

import com.example.hometask2.model.User;
import com.example.hometask2.service.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyController {


    private final UserDao userDao;

    @GetMapping(path = {"/" , "/search"})
    public String userList(Model model, String name,User user){
        if(name != null){
            model.addAttribute("getUsers", userDao.getByName(name));
            model.addAttribute("user",User.builder().build());
        }else {
            model.addAttribute("getUsers", userDao.getAll());
            model.addAttribute("user",User.builder().build());
        }
        return "index";
    }

    @PostMapping("/save")
    public ModelAndView insertUsers(User user){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user",user);
        mv.addObject("getUsers",userDao.getAll());
        userDao.createUser(user);
        return new ModelAndView("redirect:/");
    }




    @GetMapping("/delete")
    public String deleteUser(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        userDao.deleteById(id);
        return "redirect:/";
    }
}
