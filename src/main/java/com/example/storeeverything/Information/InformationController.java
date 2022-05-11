package com.example.storeeverything.Information;

import com.example.storeeverything.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/informations")
public class InformationController {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationController(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Information>> getAll() {
        return new ResponseEntity<>(informationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        try {
            Information information = informationRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(information, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(String.format("Information with Id=%d not found", id), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Information data) {
        data.setId(0);
        if (! informationRepository.findAll().contains(data)) {
            informationRepository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(String.format("%s already exists", data), HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            Information information = informationRepository.findById(id).orElseThrow();
            informationRepository.deleteById(id);
            return new ResponseEntity<>(information, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(String.format("Information with Id=%d not found", id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Information> put(@RequestBody Information data, @PathVariable int id) {
        data.setId(id);
        informationRepository.save(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
