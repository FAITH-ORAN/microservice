package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
//@JsonFilter("monFiltreDynamique")

@Entity
public class Product {

    @Id
    private int id;

    @Size(min =3,max = 25)
    private String nom;
    @Min(value = 1)
    private int prix;

    // information que nous ne souhaitons pas exposer

    private int prix_achat;

    //constructor
    public Product() {
    }

    public Product(int id, String nom, int prix, int prix_achat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prix_achat = prix_achat;
    }

    // getters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public int getPrix_achat() {
        return prix_achat;
    }


    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setPrix_achat(int prix_achat) {
        this.prix_achat = prix_achat;
    }

    //to String

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prix_achat=" + prix_achat +
                '}';
    }
}
