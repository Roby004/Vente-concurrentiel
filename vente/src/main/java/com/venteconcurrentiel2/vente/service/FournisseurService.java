package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.dto.FournisseurDto;
import com.venteconcurrentiel2.vente.model.Fournisseur;

public interface FournisseurService {
    Fournisseur enregistrer(FournisseurDto fournisseurDto);
    Fournisseur findByMail(String mail);
    Fournisseur validateFournisseur(String mail, String password);
    Fournisseur getFournisseurById(Long idFr);
}
