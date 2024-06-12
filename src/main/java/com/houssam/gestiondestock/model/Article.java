package com.houssam.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idArticle;

    @Column(name = "dateCreation" )
    private Instant dateCreation;

    @Column(name = "dateModification")
    private Instant dateModification;

    @PrePersist
    void prePersist(){
        dateCreation = Instant.now();
    }
    @PreUpdate
    void preUpdate(){
        dateModification = Instant.now();
    }
    @Column(name = "codeArticle")
    private String codeArticle;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prixHT")
    private BigDecimal prixunitaireHT;
    @Column(name = "tauxTVA")
    private BigDecimal tauxTva;

    @Column(name = "prixTTC")
    private BigDecimal prixunitaireTtc;
    @Column(name = "photo")
    private String photo;
    @ManyToOne
    @JoinColumn(name = "categorieFK")
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name = "entrepriseFK")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MouvementStock> mouvementStocks;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneCmdClient> ligneCmdClients;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneCmdFournisseur> ligneCmdFournisseurs;
}
