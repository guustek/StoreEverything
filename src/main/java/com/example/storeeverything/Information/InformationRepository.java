package com.example.storeeverything.Information;

import com.example.storeeverything.User.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InformationRepository extends JpaRepository<Information,Integer> {

    List<Information> findBySharedTrue();

    List<Information> findByUser(User user);
}
