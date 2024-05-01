package com.venteconcurrentiel2.vente.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venteconcurrentiel2.vente.model.Produit;
import com.venteconcurrentiel2.vente.service.ProduitService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Produits")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService prodService;
    @GetMapping
    public ResponseEntity<List<Produit>> getProduits(){

        return new ResponseEntity<> (prodService.getProduits(),HttpStatus.FOUND);
    }

    @PostMapping
    public Produit ajoutProduit(@RequestBody Produit produit, @RequestParam("imgPro") MultipartFile imgPro){
        return prodService.ajoutProduit(produit, imgPro);

    }

    @PutMapping("/Modifier/{id}")
    public Produit modifProduit(@RequestBody Produit produit, @PathVariable Long id){
        return prodService.modifProduit(produit, id);
    }

    @DeleteMapping("/Supprimer/{id}")
    public void suppProduit(@PathVariable Long id){
        prodService.suppProduit(id);

    }

    public Produit getProduitById(@PathVariable Long id){
        return prodService.getProduitById(id);
    }

}
