package com.example.djibyette.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String entreprise;
    @Column(length =50 )
    private String raisonsocial;
    @OneToMany(mappedBy ="partenaire")
    //@JsonBackReference
    private List<User> users;

    @OneToMany(mappedBy ="partenaire")
    //@JsonBackReference
    private List<Compte> comptes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getRaisonsocial() {
        return raisonsocial;
    }

    public void setRaisonsocial(String raisonsocial) {
        this.raisonsocial = raisonsocial;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAdressesociete() {
        return adressesociete;
    }

    public void setAdressesociete(String adressesociete) {
        this.adressesociete = adressesociete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @Column(length =50 )
    private String ninea;
    @Column(length = 15)
    private String adressesociete;
    @Column(length = 15)
    private String status;

    public Partenaire(){}

    public Partenaire(String entreprise,String raisonsocial, String ninea,  String adressesociete, String status){
        this.entreprise = entreprise;
        this.raisonsocial = raisonsocial;
        this.ninea = ninea;
        this.adressesociete = adressesociete;
        this.status = status;
    }
}
