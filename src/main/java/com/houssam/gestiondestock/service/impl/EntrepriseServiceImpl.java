package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.EntrepriseDto;
import com.houssam.gestiondestock.mapper.EntrepriseMapper;
import com.houssam.gestiondestock.model.Entreprise;
import com.houssam.gestiondestock.repository.EntrepriseRepository;
import com.houssam.gestiondestock.service.EntrepriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EntrepriseServiceImpl")
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Autowired
    EntrepriseMapper entrepriseMapper;


    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        Entreprise entreprise = entrepriseMapper.entrepriseDtoToEntreprise(entrepriseDto);
        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.save(entrepriseMapper.entrepriseDtoToEntreprise(entrepriseDto)));
    }

    @Override
    public void delete(Integer id) {
        entrepriseRepository.deleteById(id);

    }


    @Override
    public EntrepriseDto getReferenceById(Integer id) {

        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.getReferenceById(id));
    }

    @Override
    public EntrepriseDto findByNomIgnoreCase(String nom) {

        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByNomIgnoreCase(nom));
    }

    @Override
    public EntrepriseDto findByDescriptionLike(String description) {

        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByDescriptionLike(description));
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String codeFiscal) {

        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByCodeFiscal(codeFiscal));
    }

    @Override
    public EntrepriseDto findByNumTel(String numTel) {

        return entrepriseMapper.entrepriseToEntrepriseDto(entrepriseRepository.findByNumTel(numTel));
    }
}
