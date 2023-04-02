package com.ecommerce.microcommerce.web.controller;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.dao.ProductDao;
import com.ecommerce.microcommerce.web.exeptions.ProductIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
 private ProductDao productDao;

 //Récuperer la liste des produits
    @RequestMapping(value= "test/produits",method = RequestMethod.GET)
    public MappingJacksonValue listeProduits(){
        Iterable<Product> produits = productDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prix_achat");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique",
                monFiltre);
        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
        produitsFiltres.setFilters(listDeNosFiltres);
        return produitsFiltres;
    }

    //public ProductController(ProductDao productDao) {
        //this.productDao = productDao;
    //}

    //lister tous les produits
    //@GetMapping("/Produits")
  //public MappingJacksonValue listeProduits(){
        //List<Product> produits = productDao.findAll();
        //SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        //FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique",
                //monFiltre);
       // MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
        //produitsFiltres.setFilters(listDeNosFiltres);
        //return produitsFiltres;

    //}

    //liste un seul produit
    @GetMapping("produits/{id}")
    public Product afficheUnProduut(@PathVariable int id){
       Product product = productDao.findById(id);
       if(product == null)throw new ProductIntrouvableException("le produit avec l'id" + id +" est introuvable");
       return product;

    }

    //trouver une liste de produit dont le prix est supérrieur à ...
    @GetMapping("test/produits/{prixLimit}")
    public List<Product> testeDeRequetes(@PathVariable int prixLimit){
        return productDao.findByPrixGreaterThan(400);
    }

    //ajouter un produit
    @PostMapping(value = "test/produits")
    public ResponseEntity<Product> ajouterProduit(@Valid @RequestBody Product product){
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

    //supprimer un produit
    @DeleteMapping (value= "test/produits/{id}")
    public void supprimerProduit(@PathVariable int id){
        productDao.deleteById(id);
    }

    //mettre à jour un produit
    @PutMapping(value ="test/produits")
    public void updateProduit(@RequestBody Product product){
        productDao.save(product);
    }

}
