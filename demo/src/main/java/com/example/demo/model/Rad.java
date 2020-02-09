package com.example.demo.model;

import com.example.demo.utils.StringListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naslov;

    @Column
    private String apstrakt;

    @Column
    private double cena;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NaucnaOblast naucnaOblast;

    @Column
    private Boolean prihvacen;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Korisnik> koautori = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    private List<String> kljucneReci = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Casopis casopis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public Boolean getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(Boolean prihvacen) {
        this.prihvacen = prihvacen;
    }

    public List<Korisnik> getKoautori() {
        return koautori;
    }

    public void setKoautori(List<Korisnik> koautori) {
        this.koautori = koautori;
    }

    public List<String> getKljucneReci() {
        return kljucneReci;
    }

    public void setKljucneReci(List<String> kljucneReci) {
        this.kljucneReci = kljucneReci;
    }

    public Casopis getCasopis() {
        return casopis;
    }

    public void setCasopis(Casopis casopis) {
        this.casopis = casopis;
    }

    public List<Korisnik> getKorisniciPlatili() {
        return korisniciPlatili;
    }

    public void setKorisniciPlatili(List<Korisnik> korisniciPlatili) {
        this.korisniciPlatili = korisniciPlatili;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Korisnik> korisniciPlatili = new ArrayList<>();


}
