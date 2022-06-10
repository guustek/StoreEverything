package com.example.storeeverything.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
            value = "select * from category where id = (select category_id from information where user_id = ?1)",
            nativeQuery = true)
    List<Category> findByUserId(int userId);
}
