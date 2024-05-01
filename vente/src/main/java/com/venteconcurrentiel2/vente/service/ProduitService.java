package com.venteconcurrentiel2.vente.service;
import com.venteconcurrentiel2.vente.model.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProduitService {
    Produit ajoutProduit(Produit produit, MultipartFile imgPro);
    List<Produit> getProduits();
    Produit modifProduit(Produit produit, Long id);
    Produit getProduitById(Long id);
    void suppProduit(Long id);
}
