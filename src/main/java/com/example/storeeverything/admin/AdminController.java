package com.example.storeeverything.admin;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.information.Information;
import com.example.storeeverything.user.User;
import com.example.storeeverything.user.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.addAttribute("users", userRepository.findAll());
        return "admin/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/save")
    public String put(@Valid @ModelAttribute("users") List<User> userList, Model model) {
        userRepository.saveAll(userList);
        return "redirect:/admin";

    }


}
