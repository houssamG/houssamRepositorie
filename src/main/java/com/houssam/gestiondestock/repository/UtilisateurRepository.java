package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Role;
import com.houssam.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur save(Role Utilisateur);

    Utilisateur getReferenceById(Integer id);

    Utilisateur findByNomAndPrenom(String nom, String prenom);

    @Query(value = "select u from Utilisateur u inner join " +
            "u.entreprise e on e.id =:idEntreprise")
    List<Utilisateur> findUtilisateursByEntreprise(Integer idEntreprise);

}
