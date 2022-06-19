package com.example.storeeverything.information;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.category.CategoryService;
import com.example.storeeverything.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/informations")
@AllArgsConstructor
public class InformationController {

    private final InformationService informationService;
    private final CategoryService categoryService;


    @GetMapping("")
    public String getByUser(@AuthenticationPrincipal User user, @RequestParam(value = "categoryFilter", required = false) String categoryFilter, Model model) {
        List<Information> informations = informationService.getUserInformations(user);
        Set<Category> categories = categoryService.getUserCategories(user);
        if (categoryFilter != null)
            informations = informations.stream()
                    .filter(information -> information.getCategory().getName().equals(categoryFilter))
                    .toList();
        model.addAttribute("informations", informations);
        model.addAttribute("categories", categories);
        return "informations/informations";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Information information = informationService.getInformationById(id);
        model.addAttribute("information", information);
        return "informations/details";
    }

    @GetMapping("/add")
    public String add(@AuthenticationPrincipal User user, Model model) {
        Information information = new Information();
        Set<Category> categories = categoryService.getUserCategories(user);
        model.addAttribute("information", information);
        model.addAttribute("categories", categories);
        return "informations/add";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user, @Valid @ModelAttribute("information") Information information, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Set<Category> categories = categoryService.getUserCategories(user);
            model.addAttribute("categories", categories);
            return "informations/add";
        }
        information.setUser(user);
        informationService.saveInformation(information);

        return "redirect:/informations";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        informationService.deleteById(id);
        return "redirect:/informations";
    }

    @GetMapping("/{id}/edit")
    public String put(@AuthenticationPrincipal User user, @PathVariable int id, Model model) {
        Information information = informationService.getInformationById(id);
        Set<Category> categories = categoryService.getUserCategories(user);
        model.addAttribute("information", information);
        model.addAttribute("categories", categories);
        return "informations/edit";
    }

    @PostMapping("/{id}/edit")
    public String put(@AuthenticationPrincipal User user, @Valid @ModelAttribute("information") Information information, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Set<Category> categories = categoryService.getUserCategories(user);
            model.addAttribute("categories", categories);
            return "informations/edit";
        }
        information.setUser(user);
        informationService.saveInformation(information);
        return "redirect:/informations";
    }

    @GetMapping("/{id}/share-for-all")
    public String shareForAll(@PathVariable int id) {
        informationService.shareInformationForAll(id);
        return "redirect:/informations/" + id;
    }

    @GetMapping("/shared-for-all")
    public String getSharedForAll(Model model) {
        List<Information> informations = informationService.getSharedForAll();
        model.addAttribute("informations", informations);
        return "shared/sharings";
    }

    @GetMapping("/{id}/share-by-link")
    public String shareByLink(@PathVariable int id, HttpServletRequest request) {
        informationService.shareInformationByLink(id, request);
        return "redirect:/informations/" + id;
    }


    @GetMapping("/shared/{uuid}")
    public String getSharedByLink(Model model, HttpServletRequest request, @PathVariable String uuid) {
        Information information = informationService.getSharedByLink(request);
        model.addAttribute("information", information);
        return "shared/sharedByLink";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, true));
    }
}
