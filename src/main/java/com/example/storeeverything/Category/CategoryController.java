package com.example.storeeverything.Category;

import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        try {
            Category category = repository.findById(id).orElseThrow();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Category> add(@RequestBody Category data) {
        data.setId(0);
        if (! repository.findAll().contains(data)) {
            repository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Category> delete(@PathVariable int id) {
        try {
            Category category = repository.findById(id).orElseThrow();
            repository.deleteById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> put(@RequestBody Category data, @PathVariable int id) {
        data.setId(id);
        repository.save(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
