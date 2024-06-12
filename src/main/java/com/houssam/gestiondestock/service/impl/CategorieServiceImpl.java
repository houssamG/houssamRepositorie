package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.CategorieDto;
import com.houssam.gestiondestock.mapper.CategorieMapper;
import com.houssam.gestiondestock.model.Categorie;
import com.houssam.gestiondestock.repository.CategorieRepository;
import com.houssam.gestiondestock.service.CategorieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CategorieServiceImpl")
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    CategorieMapper categorieMapper;

    @Override
    public CategorieDto save(CategorieDto categorieDto) {

        return categorieMapper.categorieToCategorieDto(categorieRepository.save(categorieMapper.categorieDtoToCategorie(categorieDto)));
    }

    @Override
    public void delete(Integer id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public CategorieDto getReferenceById(Integer id) {

        return categorieMapper.categorieToCategorieDto(categorieRepository.getReferenceById(id));
    }

    @Override
    public CategorieDto findByCode(String code) {

        return categorieMapper.categorieToCategorieDto(categorieRepository.findByCode(code));
    }

    @Override
    public List<CategorieDto> findByDesignationLike(String designation) {
        List<Categorie> categories = categorieRepository.findByDesignationLike(designation);
        List<CategorieDto> categoriesDtos = new ArrayList<>();
        categories.stream().forEach(categorie -> {
            categoriesDtos.add(categorieMapper.categorieToCategorieDto(categorie));
        });
        return categoriesDtos;
    }
}
