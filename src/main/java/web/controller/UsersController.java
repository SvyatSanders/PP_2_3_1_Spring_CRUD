package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImpl;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired UserDaoImpl userDao;

    @GetMapping()
    public String list(Model model) {
        if (userDao.getUsers().isEmpty()) {
            userDao.saveUser(new User("Tom", "Holland", 23, "male"));
            userDao.saveUser(new User("Ben", "Howard", 42, "male"));
        }

        model.addAttribute("users", userDao.getUsers());
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

    // @ModelAttribute - автоматически передает объект User с заполненными полями и помещает его в model.addAttribute
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userDao.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDao.deleteUser(id);
        return "redirect:/users";
    }

}
