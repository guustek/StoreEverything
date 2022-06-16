package com.example.storeeverything.information;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.category.CategoryRepository;
import com.example.storeeverything.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

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
    public String getByUserId(@AuthenticationPrincipal User user, Model model) {
        List<Information> informations = informationRepository.findByUserId(user.getId());
        informations.sort(Comparator.comparing(Information :: getId));
        model.addAttribute("informations", informations);
        return "informations/informations";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Information information = informationRepository.findById(id).orElseThrow();
        model.addAttribute("information", information);
        return "informations/details";
    }

    @GetMapping("/add")
    public String add(@AuthenticationPrincipal User user, Model model) {
        Information information = new Information();
        List<Category> categories = categoryRepository.findByUserId(user.getId());
        model.addAttribute("information", information);
        model.addAttribute("categories", categories);
        return "informations/add";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user, @Valid @ModelAttribute("information") Information information, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryRepository.findByUserId(user.getId());
            model.addAttribute("categories", categories);
            return "informations/add";
        }
        information.setId(0);
        correctInformationValues(user, information);
        informationRepository.save(information);

        return "redirect:/informations";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        informationRepository.deleteById(id);
        return "redirect:/informations";
    }

    @GetMapping("/{id}/edit")
    public String put(@PathVariable int id, Model model) {
        Information information = informationRepository.findById(id).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("information", information);
        model.addAttribute("categories", categories);
        return "informations/edit";
    }

    @PostMapping("/{id}/edit")
    public String put(@AuthenticationPrincipal User user, @Valid @ModelAttribute("information") Information information, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            return "informations/edit";
        }
        correctInformationValues(user, information);
        informationRepository.save(information);
        return "redirect:/informations";
    }

    private void correctInformationValues(User user, Information information) {
        information.setUser(user);
        if (information.getLink().isEmpty())
            information.setLink(null);
        Category existingCategory = categoryRepository.findByName(information.getCategory().getName());
        if (existingCategory != null)
            information.setCategory(existingCategory);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
