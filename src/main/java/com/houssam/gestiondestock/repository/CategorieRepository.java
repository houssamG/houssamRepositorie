package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Categorie save(Categorie article);

    Categorie getReferenceById(Integer id);

    Categorie findByCode(String code);

    List<Categorie> findByDesignationLike(String designation);


}
