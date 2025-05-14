package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "objet")
public class Objet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idObjet;

    private String libObjet;

    // Constructeurs, getters et setters

    public Objet() {
    }

    public Objet(String libObjet) {
        this.libObjet = libObjet;
    }

    // Getters et setters

    public Long getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(Long idObjet) {
        this.idObjet = idObjet;
    }

    public String getLibObjet() {
        return libObjet;
    }

    public void setLibObjet(String libObjet) {
        this.libObjet = libObjet;
    }
}
