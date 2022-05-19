package com.example.storeeverything.Information;

import com.example.storeeverything.Category.Category;
import com.example.storeeverything.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/informations")
public class InformationController {

    private final InformationRepository informationRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public InformationController(InformationRepository informationRepository, CategoryRepository categoryRepository) {
        this.informationRepository = informationRepository;
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("")
    public String getAll(Model model) {
        List<Information> informations = informationRepository.findAll();
        informations.sort(Comparator.comparing(Information :: getId));
        model.addAttribute("informations", informations);
        return "informations/informations";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        try {
            Information information = informationRepository.findById(id).orElseThrow();
            model.addAttribute("information", information);
            return "informations/details";
        } catch (NoSuchElementException e) {
            //return new ResponseEntity<>(String.format("Information with Id=%d not found", id), HttpStatus.NOT_FOUND);
            /*
             * TODO return if couldn't find information with this id
             */
            return "";
        }
    }

    @GetMapping("/add")
    public String add(Model model) {
        Information information = new Information();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("information", information);
        model.addAttribute("categories", categories);
        return "informations/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("information") Information information) {
        information.setId(0);
        Date sqlDate = new Date(System.currentTimeMillis());
        information.setAddedDate(sqlDate);
        if (informationRepository.findAll().contains(information)) {
            //return new ResponseEntity<>(String.format("%s already exists", information), HttpStatus.CONFLICT);
            /*
             * TODO return if found existing information
             */
            return "";
        }
        informationRepository.save(information);

        return "redirect:/informations";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        try {
            Information information = informationRepository.findById(id).orElseThrow();
            informationRepository.deleteById(id);
            return "redirect:/informations";
        } catch (NoSuchElementException e) {
            //return new ResponseEntity<>(String.format("Information with Id=%d not found", id), HttpStatus.NOT_FOUND);
            /*
             * TODO return if couldn't find information with this id
             */
            return "";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        try {
            Information information = informationRepository.findById(id).orElseThrow();
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("information", information);
            model.addAttribute("categories", categories);
            return "informations/edit";
        } catch (NoSuchElementException e) {
            /*
             * TODO return if couldn't find category with this id
             */
            return "";
        }
    }

    @PostMapping("/{id}/edit")
    public String put(@ModelAttribute("information") Information information, @PathVariable int id) {
        informationRepository.save(information);
        return "redirect:/informations/" + id;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
