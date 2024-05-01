package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.exception.ProduitAlreadyExistsException;
import com.venteconcurrentiel2.vente.exception.ProduitNotFoundException;
import com.venteconcurrentiel2.vente.model.Produit;
import com.venteconcurrentiel2.vente.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduitImpService implements ProduitService{
    private final ProduitRepository produitRepository;
    @Override
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit ajoutProduit(Produit produit, MultipartFile imgPro) {
        if (produitExist(produit.getIdPro())) {
            throw new ProduitAlreadyExistsException("Le produit " + produit.getDesign() + " est deja enregistre");
        }
        try {
            produit.setImgPro(imgPro.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        }
        return produitRepository.save(produit);
    }

    @Override
    public Produit modifProduit(Produit produit, Long id) {
        Optional<Produit> existProduit = produitRepository.findById(id);

        // si id n'existe pas
        if (!existProduit.isPresent()) {
            throw new ProduitNotFoundException("Produit avec pour ID " + id + " non trouve.");
        }

        Produit pr = existProduit.get();
        pr.setCat(produit.getCat());
        pr.setDateAjout(produit.getDateAjout());
        pr.setDescr(produit.getDescr());
        pr.setDesign(produit.getDesign());
        pr.setImgPro(produit.getImgPro());
        pr.setNbClic(produit.getNbClic());
        pr.setPrix(produit.getPrix());
        pr.setQte(produit.getQte());

        // Save the updated product
        return produitRepository.save(pr);
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new ProduitNotFoundException("Desole, le produit est introuvable"));
    }

    @Override
    public void suppProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new ProduitNotFoundException("Produit avec pour ID " + id + " non trouve.");
        }

    }
    private boolean produitExist(Long idPro) {

        return produitRepository.findById(idPro).isPresent();
    }
}
