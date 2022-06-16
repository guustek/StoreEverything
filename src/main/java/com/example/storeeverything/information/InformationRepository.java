package com.example.storeeverything.information;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationRepository extends JpaRepository<Information,Integer> {

    List<Information> findByUserId(int id);

    List<Information> findBySharedTrue();
}
