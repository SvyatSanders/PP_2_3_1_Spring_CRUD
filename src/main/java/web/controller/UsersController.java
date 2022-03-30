package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserDaoImpl userDao;

    @GetMapping()
    public String list(Model model) {
        List<User> users = userDao.getUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable("id") final int id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        return "users/user";
    }


    // передаем в Model model новый пустой объект User user
    // в форме "users/new" заполняем поля объекта User user
    // заполненный новый объект User user передаем методу контроллера createUser()
    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "users/new";
    }

    // @ModelAttribute - автоматически передает обхект User с заполненными полями и помещает его в model.addAttribute
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        user.setId(userDao.getUsers().size() + 1);
        userDao.saveUser(user);
        return "redirect:/users";
    }
}
