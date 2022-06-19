package com.example.storeeverything.category;

import com.example.storeeverything.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Set<Category> getUserCategories(User user) {
        return categoryRepository.findByUserId(user.getId());
    }

    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
