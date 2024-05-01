package com.venteconcurrentiel2.vente.repository;

import com.venteconcurrentiel2.vente.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    Fournisseur findByMailFr(String mail);
}