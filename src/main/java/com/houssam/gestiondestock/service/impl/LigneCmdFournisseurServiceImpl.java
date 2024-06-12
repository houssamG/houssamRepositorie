package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.LigneCmdFournisseurDto;
import com.houssam.gestiondestock.mapper.LigneCmdFournisseurMapper;
import com.houssam.gestiondestock.model.LigneCmdFournisseur;
import com.houssam.gestiondestock.repository.LigneCmdFournisseurRepository;
import com.houssam.gestiondestock.service.LigneCmdFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("LigneCmdFournisseurServiceImpl")
@Slf4j
public class LigneCmdFournisseurServiceImpl implements LigneCmdFournisseurService {
    @Autowired
    LigneCmdFournisseurRepository ligneCmdFournisseurRepository;
    @Autowired
    LigneCmdFournisseurMapper ligneCmdFournisseurMapper;

    @Override
    public LigneCmdFournisseurDto save(LigneCmdFournisseurDto ligneCmdFournisseurDto) {

        return ligneCmdFournisseurMapper.ligneCmdFournisseurToLigneCmdFournisseurDto
                (ligneCmdFournisseurRepository
                        .save(ligneCmdFournisseurMapper.ligneCmdFournisseurDtoToLigneCmdFournisseur
                                (ligneCmdFournisseurDto)));
    }

    @Override
    public void delete(Integer id) {
        ligneCmdFournisseurRepository.deleteById(id);

    }


    @Override
    public LigneCmdFournisseurDto getReferenceById(Integer id) {

        return ligneCmdFournisseurMapper.ligneCmdFournisseurToLigneCmdFournisseurDto
                (ligneCmdFournisseurRepository.getReferenceById(id));
    }

    @Override
    public List<LigneCmdFournisseurDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire) {


        List<LigneCmdFournisseur> ligneCmdFournisseurs = ligneCmdFournisseurRepository.findByPrixunitaireGreaterThan(prixunitaire);
        List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = new ArrayList<>();
        ligneCmdFournisseurs.stream().forEach(ligneCmdFournisseur -> {
            ligneCmdFournisseurDtos.add(
                    ligneCmdFournisseurMapper.ligneCmdFournisseurToLigneCmdFournisseurDto(
                            ligneCmdFournisseur));
        });
        return ligneCmdFournisseurDtos;

    }

    @Override
    public List<LigneCmdFournisseurDto> findByArticle(Integer idArticle) {


        List<LigneCmdFournisseur> ligneCmdFournisseurs = ligneCmdFournisseurRepository.findByArticle(idArticle);
        List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = new ArrayList<>();
        ligneCmdFournisseurs.stream().forEach(ligneCmdFournisseur -> {
            ligneCmdFournisseurDtos.add(
                    ligneCmdFournisseurMapper.ligneCmdFournisseurToLigneCmdFournisseurDto(
                            ligneCmdFournisseur));
        });
        return ligneCmdFournisseurDtos;

    }

    @Override
    public List<LigneCmdFournisseurDto> findLigneCmdClientByFournisseurNom(String nomFournisseur) {
        {

            List<LigneCmdFournisseur> ligneCmdFournisseurs = ligneCmdFournisseurRepository.findLigneCmdFournisseurByFournisseurNom(nomFournisseur);
            List<LigneCmdFournisseurDto> ligneCmdFournisseurDtos = new ArrayList<>();
            ligneCmdFournisseurs.stream().forEach(ligneCmdFournisseur -> {
                ligneCmdFournisseurDtos.add(
                        ligneCmdFournisseurMapper.ligneCmdFournisseurToLigneCmdFournisseurDto(
                                ligneCmdFournisseur));
            });
            return ligneCmdFournisseurDtos;
        }
    }
}
