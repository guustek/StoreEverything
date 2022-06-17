package com.example.storeeverything.information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InformationRepository extends JpaRepository<Information,Integer> {

    List<Information> findByUserId(int id);

    List<Information> findBySharedForAllTrue();

    Optional<Information> findByShareLink(String link);
}
