package com.example.demo.repo;

import com.example.demo.model.NaucnaOblast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NaucnaOblastRepo extends JpaRepository<NaucnaOblast, String> {
    List<NaucnaOblast> findAll();

    NaucnaOblast findOneBySifra(int sifra);
}
