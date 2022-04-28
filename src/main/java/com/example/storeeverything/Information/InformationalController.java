package com.example.storeeverything.Information;

import com.example.storeeverything.Category.Category;
import com.example.storeeverything.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/information")
public class InformationalController {

    private final InformationRepository repository;

    @Autowired
    public InformationalController(InformationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Information> getAll() {
        return repository.findAll();
    }
}
