package com.example.storeeverything.home;

import com.example.storeeverything.information.Information;
import com.example.storeeverything.information.InformationService;
import com.example.storeeverything.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final InformationService informationService;

    @GetMapping("")
    public String getAll(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            List<Information> informations = informationService.getInformationsWithReminders(user);
            model.addAttribute("informations", informations);
        }
        return "home/home";
    }
}
