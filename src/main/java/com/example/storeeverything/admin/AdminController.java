package com.example.storeeverything.admin;

import com.example.storeeverything.user.User;
import com.example.storeeverything.user.UserListDTO;
import com.example.storeeverything.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin")

public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String getAllUser(Model model) {
        System.out.println(userRepository.findAll());
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", new UserListDTO(userList));
        return "admin/admin";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("userList") UserListDTO userList) {
        List<User> users = userList.getUsers();
        userRepository.saveAll(users);
        return "redirect:/admin";
    }
}
