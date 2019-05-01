package com.controller;

import com.model.Role;
import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class MainController {

    private UserService userService;
    private RoleService roleService;

    public MainController(){}

    @Autowired
    public MainController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    @PostMapping
    public String main(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "main";
    }

    @RequestMapping(value = "/admin/show", method = {RequestMethod.GET, RequestMethod.POST})
    public String show(Model model, HttpServletResponse resp) throws IOException{

        model.addAttribute("users", userService.getAllUsers());
        return "/admin/showUsers";
    }

    @GetMapping(value = "/admin/add")
    public String getAddPage() {
        return "admin/addUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUserMethod(@RequestParam("name") String name,
                              @RequestParam("login") String login,
                              @RequestParam("password") String password,
                              @RequestParam("roles") String role) throws IOException {
        Set<Role> roles = getRoles(role);
        userService.addUser(name, login, password, roles);
        return "redirect:/admin/show";
    }


    @GetMapping(value = "/admin/update")
    public String getModificationPage(@RequestParam("id") String id, HttpServletResponse resp, Model model)
            throws IOException {

        if (id.isEmpty()) {
            resp.sendRedirect("/admin/show");
            return null;
        }

        Long long_id = Long.parseLong(id);
        User userById = userService.getUserById(long_id);

        if (userById != null) {
            model.addAttribute("editUser", userById);
            return "/admin/update";
        }

        resp.sendRedirect("/admin/show");
        return null;
    }

    @PostMapping(value = "/admin/update")
    public void updateMethod(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("roles") String role,
                             HttpServletResponse resp) throws IOException {
        Set<Role> roles = getRoles(role);
        userService.updateUser(id, name, login, password, roles);
        resp.sendRedirect("/admin/show");
    }

    @GetMapping(value = "/admin/remove")
    public void removeMethod(@RequestParam("id") String id, HttpServletResponse resp) throws IOException {

        if (id.isEmpty()) {
            resp.sendRedirect("/admin/show");
            return;
        }

        long parseLong = Long.parseLong(id);
        userService.removeUserById(parseLong);
        resp.sendRedirect("/admin/show");

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public String user() {
        return "user";
    }

    private Set<Role> getRoles(String role) {
        Set<Role> roles = new HashSet<>();
        Role role_admin = roleService.getRoleById(1L);
        Role role_user = roleService.getRoleById(2L);

        switch (role) {
            case "admin":
                roles.add(role_admin);
                break;
            case "admin,user":
            case "user,admin":
                roles.add(role_admin);
                roles.add(role_user);
                break;
            default:
                roles.add(role_user);
                break;
        }

        return roles;
    }
}
