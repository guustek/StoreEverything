package com.example.storeeverything.Information;

import com.example.storeeverything.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information,Integer> {
}
