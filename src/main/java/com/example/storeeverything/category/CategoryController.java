package com.example.storeeverything.category;

import com.example.storeeverything.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getByUserId(@AuthenticationPrincipal User user, Model model) {
        Set<Category> categories = service.getUserCategories(user);
        model.addAttribute("categories", categories);
        return "categories/categories";
    }
}
