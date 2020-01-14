package com.example.demo.repo;

import com.example.demo.model.Casopis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasopisRepo extends JpaRepository<Casopis, String> {
    List<Casopis> findAll();

    Casopis findOneByIme(String ime);
    Casopis findOneByIssn(Long issn);
}
