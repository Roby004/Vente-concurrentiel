package com.venteconcurrentiel2.vente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPro;
    private String design;

    @Column(nullable = false)
    private Integer prix;

    @Column(nullable = false)
    private String descr;

    @Column(nullable = false)
    private Integer qte;

    @Lob
    private byte[] imgPro;

    private  String cat;
    private Integer nbClic;
    private Date dateAjout;

    @ManyToOne
    @JoinColumn(name = "idFr", nullable = false)
    private Fournisseur fourniss;
}
