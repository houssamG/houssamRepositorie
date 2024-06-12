package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.FournisseurDto;
import com.houssam.gestiondestock.mapper.FournisseurMapper;
import com.houssam.gestiondestock.model.Fournisseur;
import com.houssam.gestiondestock.repository.FournisseurRepository;
import com.houssam.gestiondestock.service.FournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("FournisseurServiceImpl")
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    @Autowired
    FournisseurRepository fournisseurRepository;
    @Autowired
    FournisseurMapper fournisseurMapper;

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {

        return fournisseurMapper.fournisseurToFournisseurDto(fournisseurRepository.save(fournisseurMapper.fournisseurDtoToFournisseur(fournisseurDto)));
    }

    @Override
    public void delete(Integer id) {
        fournisseurRepository.deleteById(id);

    }


    @Override
    public FournisseurDto getReferenceById(Integer id) {

        return fournisseurMapper.fournisseurToFournisseurDto(fournisseurRepository.getReferenceById(id));
    }

    @Override
    public FournisseurDto findByNomAndPrenom(String nom, String prenom) {

        return fournisseurMapper.fournisseurToFournisseurDto(fournisseurRepository.findByNomAndPrenom(nom, prenom));
    }

    @Override
    public List<FournisseurDto> findByDateCommandeAfter(Instant dateCommande) {
        List<Fournisseur> fournisseurs = fournisseurRepository.findFournisseurByDateCommandeAfter(dateCommande);
        List<FournisseurDto> fournisseursDtos = new ArrayList<>();
        fournisseurs.stream().forEach(fournisseur -> {
            fournisseursDtos.add(fournisseurMapper.fournisseurToFournisseurDto(fournisseur));
        });
        return fournisseursDtos;
    }
}
