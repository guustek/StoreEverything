package com.example.storeeverything.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
            value = "select * from category where id in (select category_id from information where user_id = ?1) order by id",
            nativeQuery = true)
    Set<Category> findByUserId(int userId);

    Optional<Category> findByName(String name);

}
