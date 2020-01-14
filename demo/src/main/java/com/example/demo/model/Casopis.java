package com.example.demo.model;


import org.apache.ibatis.annotations.Many;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Casopis {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ime", nullable=false)
    private String ime;

    @Column(name = "issn", nullable = false)
    private Long issn;

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
//    @JoinTable(name = "casopis_nobl",
//            joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "nau_obl_id", referencedColumnName = "sifra"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "casopis_oblasti")
    private List<NaucnaOblast> naucneOblasti;

    @Column(name = "clanarina", nullable=false)
    private Boolean clanarina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Long getIssn() {
        return issn;
    }

    public void setIssn(Long issn) {
        this.issn = issn;
    }

    public List<NaucnaOblast> getNaucneOblasti() {
        return naucneOblasti;
    }

    public void setNaucneOblasti(List<NaucnaOblast> naucneOblasti) {
        this.naucneOblasti = naucneOblasti;
    }

    public Boolean getClanarina() {
        return clanarina;
    }

    public void setClanarina(Boolean clanarina) {
        this.clanarina = clanarina;
    }


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "casopis_urednici", joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "urednik_id", referencedColumnName = "username"))
    private List<Korisnik> urednici;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "casopis_recenzenti", joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recenzent_id", referencedColumnName = "username"))
    private List<Korisnik> recenzenti;

    public void setRecenzenti(List<Korisnik> recenzenti) {
        this.recenzenti = recenzenti;
    }

    public void setUrednici(List<Korisnik> urednici){
        this.urednici = urednici;
    }

    public List<Korisnik> getRecenzenti(){
        return this.recenzenti;
    }

    public List<Korisnik> getUrednici(){
        return this.urednici;
    }

    @Column(name = "glavni_urednik")
    private String glavni_urednik;

    public String getGlavni_urednik() {
        return glavni_urednik;
    }

    public void setGlavni_urednik(String glavni_urednik) {
        this.glavni_urednik = glavni_urednik;
    }

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
//    @JoinTable(name = "casopis_placanja", joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "placanje_sifra", referencedColumnName = "sifra"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "casopis_nacini_placanja")
    private List<NacinPlacanja> naciniPlacanja;

    public void setNaciniPlacanja(List<NacinPlacanja> naciniPlacanja){
        this.naciniPlacanja = naciniPlacanja;
    }

    public List<NacinPlacanja> getNaciniPlacanja(){
        return this.naciniPlacanja;
    }

    @Column(name = "aktivan")
    private Boolean aktivan;

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }
}
