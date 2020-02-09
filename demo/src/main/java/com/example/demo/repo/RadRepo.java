package com.example.demo.repo;

import com.example.demo.model.Rad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadRepo extends JpaRepository<Rad, String> {
    List<Rad> findAll();

    Rad findOneById(Integer id);
    Rad findOneByNaslov(String naslov);
}
