package com.example.demo.repo;

import com.example.demo.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepo extends JpaRepository<Korisnik, String> {
    List<Korisnik> findAll();

    Korisnik findOneByUsername(String username);

}
