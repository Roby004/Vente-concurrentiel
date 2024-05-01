package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.dto.FournisseurDto;
import com.venteconcurrentiel2.vente.model.Client;
import com.venteconcurrentiel2.vente.model.Fournisseur;
import com.venteconcurrentiel2.vente.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FournisseurImplService implements FournisseurService{
    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Override
    public Fournisseur enregistrer(FournisseurDto fournisseurDto) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCompany(fournisseurDto.getCompany());
        fournisseur.setMdpFr(fournisseurDto.getMdpFr());
        fournisseur.setMailFr(fournisseurDto.getMailFr());
        fournisseur.setContactFr(fournisseurDto.getContactFr());
        fournisseur.setAdresseFr(fournisseurDto.getAdresseFr());
        fournisseur.setRole("ROLE_FOURNISSEUR"); // Assuming default role
        fournisseur.setAccesFr(false); // Assuming default access
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public Fournisseur findByMail(String mail) {
        return fournisseurRepository.findByMailFr(mail);
    }

    public Fournisseur getFournisseurById(Long idFr) {
        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFr);
        return fournisseurOptional.orElse(null);
    }

    @Override
    public Fournisseur validateFournisseur(String mail, String password) {
        Fournisseur fournisseur = fournisseurRepository.findByMailFr(mail);
        if (fournisseur != null && fournisseur.getMdpFr().equals(password)) {
            return fournisseur;
        }
        return null;
    }
}
