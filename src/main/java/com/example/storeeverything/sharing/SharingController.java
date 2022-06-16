package com.example.storeeverything.sharing;

import com.example.storeeverything.information.Information;
import com.example.storeeverything.information.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("")
public class SharingController {
    private final InformationRepository informationRepository;

    @Autowired
    public SharingController(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @GetMapping("/shared")
    public String getAll(Model model) {
        List<Information> informations = informationRepository.findBySharedTrue();
        model.addAttribute("informations", informations);
        return "informations/informations";
    }

    @GetMapping("/link/{uuid}")
    public String getSharedByLink(@PathVariable UUID uuid, HttpServletRequest httpServletRequest ){

        Information information = informationRepository.findBySharedLink(httpServletRequest.getRequestURL().toString()).orElseThrow();

        //TODO: template
        return "informations/informations";

    }
}