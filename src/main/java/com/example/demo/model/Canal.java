package com.example.demo.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "canal")
public class Canal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCanal;

    private String libCanal;
   

    // Constructeurs, getters et setters

    public Canal() {
    }

    public Canal(String libCanal) {
        this.libCanal = libCanal;
       
    }

    // Getters et setters

    public Long getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(Long idCanal) {
        this.idCanal = idCanal;
    }

    public String getLibCanal() {
        return libCanal;
    }

    public void setLibCanal(String libCanal) {
        this.libCanal = libCanal;
    }

   
}
