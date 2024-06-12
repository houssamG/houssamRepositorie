package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.CategorieDto;

import java.util.List;


public interface CategorieService {
    CategorieDto save(CategorieDto categorieDto);

    void delete(Integer id);

    CategorieDto getReferenceById(Integer id);

    CategorieDto findByCode(String code);

    List<CategorieDto> findByDesignationLike(String designation);
}
