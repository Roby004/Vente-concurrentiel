package com.venteconcurrentiel2.vente.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur {
    //@Column(name = "idFr")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFr;

    @Column(nullable = false)
    private String company;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "mdpFr", nullable = false)
    private String mdpFr;

    @Lob
    @Column(name = "imgFr")
    private Byte[] imgFr = new Byte[0];

    @NaturalId(mutable = true)
    @Column(name = "mailFr", nullable = false)
    private String mailFr;

    private String role = "ROLE_FOURNISSEUR";

    @Column(name = "contactFr", nullable = false)
    private String contactFr;

    @Column(name = "adresseFr", nullable = false)
    private String adresseFr;

    @Column(name = "accesFr")
    private Boolean accesFr;


    @OneToMany(mappedBy = "fourniss", cascade = CascadeType.ALL)
    private List<Produit> produits;
}
