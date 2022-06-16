package com.example.storeeverything.Shared;

import com.example.storeeverything.Information.Information;
import com.example.storeeverything.Information.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shared")
public class SharedController {
    private final InformationRepository informationRepository;

    @Autowired
    public SharedController(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<Information> informations = informationRepository.findBySharedTrue();
        model.addAttribute("informations", informations);
        return "informations/informations";
    }
}
