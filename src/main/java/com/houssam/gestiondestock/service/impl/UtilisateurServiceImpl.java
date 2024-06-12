package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.UtilisateurDto;
import com.houssam.gestiondestock.mapper.UtilisateurMapper;
import com.houssam.gestiondestock.model.Utilisateur;
import com.houssam.gestiondestock.repository.UtilisateurRepository;
import com.houssam.gestiondestock.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UtilisateurServiceImpl")
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    UtilisateurMapper utilisateurMapper;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        return utilisateurMapper.utilisateurToUtilisateurDto(
                utilisateurRepository.save(
                        utilisateurMapper.utilisateurDtoToUtilisateur(utilisateurDto)));
    }

    @Override
    public void delete(Integer id) {
        utilisateurRepository.deleteById(id);

    }

    @Override
    public UtilisateurDto getReferenceById(Integer id) {

        return utilisateurMapper.utilisateurToUtilisateurDto(utilisateurRepository.getReferenceById(id));
    }

    @Override
    public UtilisateurDto findByNomAndPrenom(String nom, String prenom) {

        return utilisateurMapper.utilisateurToUtilisateurDto(utilisateurRepository.findByNomAndPrenom(nom, prenom));
    }

    @Override
    public List<UtilisateurDto> findUtilisateursByEntreprise(Integer idEntreprise) {

        List<Utilisateur> utilisateurs = utilisateurRepository.findUtilisateursByEntreprise(idEntreprise);
        List<UtilisateurDto> utilisateursDtos = new ArrayList<>();
        utilisateurs.stream().forEach(utilisateur -> {
            utilisateursDtos.add(
                    utilisateurMapper.utilisateurToUtilisateurDto(
                            utilisateur));
        });
        return utilisateursDtos;

    }
}
