package com.ecommerce.microcommerce.web.controller;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.dao.ProductDao;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {
 private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    //lister tous les produits
    @GetMapping("/Produits")
  public MappingJacksonValue listeProduits(){
        List<Product> produits = productDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique",
                monFiltre);
        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
        produitsFiltres.setFilters(listDeNosFiltres);
        return produitsFiltres;

    }

    //liste un seul produit
    @GetMapping("/Produits/{id}")
    public Product afficheUnProduut(@PathVariable int id){
        return productDao.findById(id);
    }

    //ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Product> ajouterProduit(@RequestBody Product product){
        Product productAdded = productDao.save(product);
        if(Objects.isNull(productAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
