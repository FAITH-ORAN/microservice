package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonFilter("monFiltreDynamique")

public class Product {

    private int id;
    private String nom;
    private int prix;

    // information que nous ne souhaitons pas exposer

    private int prixAchat;

    //constructor
    public Product() {
    }

    public Product(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
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

    public int getPrixAchat() {
        return prixAchat;
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

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    //to String
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
