package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.finedAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model, User user) {
        List<Role> roles = roleService.finedAllRoles();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "user_create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping ("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = roleService.finedAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user_update";
    }

    @PatchMapping ("/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}
