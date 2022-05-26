package com.example.storeeverything.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<Category> categories = repository.findAll();
        categories.sort(Comparator.comparing(Category :: getId));
        model.addAttribute("categories", categories);
        return "categories/categories";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        try {
            Category category = repository.findById(id).orElseThrow();
            model.addAttribute("category", category);
            return "categories/details";
        } catch (NoSuchElementException e) {
            //return new ResponseEntity<>(String.format("Category with Id=%d not found", id), HttpStatus.NOT_FOUND);
            /*
             * TODO return if couldn't find category with this id
             */
            return "";
        }
    }

    @GetMapping("/add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "categories/add";
        }
        category.setId(0);
        if (repository.findAll().contains(category)) {
            //return new ResponseEntity<>(String.format("%s already exists", category), HttpStatus.CONFLICT);
            /*
             * TODO return if found existing category
             */
            return "";
        }
        repository.save(category);

        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        try {
            Category category = repository.findById(id).orElseThrow();
            repository.deleteById(id);
            return "redirect:/categories";
        } catch (NoSuchElementException e) {
            //return new ResponseEntity<>(String.format("Category with Id=%d not found", id),HttpStatus.NOT_FOUND);
            /*
             * TODO return if couldn't find category with this id
             */
            return "";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        try {
            Category category = repository.findById(id).orElseThrow();
            model.addAttribute("category", category);
            return "categories/edit";
        } catch (NoSuchElementException e) {
            /*
             * TODO return if couldn't find category with this id
             */
            return "";
        }
    }

    /*
     * TODO get update working
     */
    @PostMapping("/{id}/edit")
    public String put(@Valid @ModelAttribute("category") Category category,BindingResult bindingResult, @PathVariable int id ) {
        if(bindingResult.hasErrors()){
            return "categories/edit";
        }
        repository.save(category);
        return "redirect:/categories/" + id;
    }
}
