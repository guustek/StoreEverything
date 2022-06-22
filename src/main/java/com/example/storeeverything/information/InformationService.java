package com.example.storeeverything.information;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.category.CategoryService;
import com.example.storeeverything.exceptions.SharedByLinkNotFoundException;
import com.example.storeeverything.user.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class InformationService {

    private final InformationRepository informationRepository;
    private final CategoryService categoryService;


    public Information getInformationById(int id) {
        return informationRepository.findById(id).orElseThrow();
    }

    public List<Information> getUserInformations(User user) {
        List<Information> informations = informationRepository.findByUserId(user.getId());
        informations.sort(Comparator.comparing(Information :: getId));
        return informations;
    }

    public List<Information> getInformationsWithReminders(User user) {
        List<Information> informations = informationRepository.findByUserId(user.getId());
        informations.sort(Comparator.comparing(Information :: getId));
        return informations.stream()
                .filter(information -> {
                    if (information.getRemindDate() != null) {
                        return information.getRemindDate().equals(LocalDate.now());
                    }
                    return false;
                }).toList();
    }

    public void saveInformation(Information information) {
        if (information.getLink().isEmpty())
            information.setLink(null);
        Optional<Category> existingCategory = categoryService.getByName(information.getCategory().getName());
        existingCategory.ifPresent(information :: setCategory);
        informationRepository.save(information);
    }

    public void deleteById(int id) {
        informationRepository.deleteById(id);
    }

    public List<Information> getSharedForAll() {
        return informationRepository.findBySharedForAllTrue();
    }

    public void shareInformationForAll(int id) {
        Information information = getInformationById(id);
        information.setSharedForAll(true);
        informationRepository.save(information);
    }

    public void removeFromPublic(int id) {
        Information information = getInformationById(id);
        information.setSharedForAll(false);
        informationRepository.save(information);
    }

    public void shareInformationByLink(int id, HttpServletRequest request) {
        Information information = getInformationById(id);
        String link = generateSharingLinkFromRequest(request);
        information.setShareLink(link);
        informationRepository.save(information);
    }

    public void stopSharingByLink(int id) {
        Information information = getInformationById(id);
        information.setShareLink(null);
        informationRepository.save(information);
    }

    public String generateSharingLinkFromRequest(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        return url.substring(0, url.length() - uri.length()) + "/informations/shared/" + UUID.randomUUID();
    }

    public Information getSharedByLink(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return informationRepository.findByShareLink(url).orElseThrow(SharedByLinkNotFoundException :: new);
    }

    public List<Information> sortInformationsByCategoryList(List<Information> informations, List<Category> tmp) {
        List<Information> sortedInformations = new ArrayList<>();
        for (Category category : tmp) {
            for (Information information : informations) {
                if (! information.getCategory().equals(category))
                    continue;
                sortedInformations.add(information);
            }
        }
        return sortedInformations;
    }
}
