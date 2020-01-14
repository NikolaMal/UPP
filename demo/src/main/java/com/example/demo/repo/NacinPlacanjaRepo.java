package com.example.demo.repo;

import com.example.demo.model.NacinPlacanja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NacinPlacanjaRepo extends JpaRepository<NacinPlacanja, String> {
    List<NacinPlacanja> findAll();

    NacinPlacanja findOneByIme(String name);
}
