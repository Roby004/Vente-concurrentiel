package com.venteconcurrentiel2.vente.repository;

import com.venteconcurrentiel2.vente.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    //Optional<Produit> findById();

}
