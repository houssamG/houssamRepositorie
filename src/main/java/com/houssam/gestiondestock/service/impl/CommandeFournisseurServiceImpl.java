package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.CommandeFournisseurDto;
import com.houssam.gestiondestock.mapper.CommandeFournisseurMapper;
import com.houssam.gestiondestock.model.CommandeFournisseur;
import com.houssam.gestiondestock.repository.CommandeFournisseurRepository;
import com.houssam.gestiondestock.service.CommandeFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("CommandeFournisseurServiceImpl")
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    @Autowired
    CommandeFournisseurRepository commandeFournisseurRepository;
    @Autowired
    CommandeFournisseurMapper commandeFournisseurMapper;

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        CommandeFournisseur commandeFournisseur = commandeFournisseurMapper.commandeFournisseurDtoToCommandeFournisseur
                (commandeFournisseurDto);
        CommandeFournisseur comdFournisseur = commandeFournisseurRepository.save(commandeFournisseur);
        return commandeFournisseurMapper.commandeFournisseurToCommandeFournisseurDto
                (comdFournisseur);
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurRepository.deleteById(id);

    }


    @Override
    public CommandeFournisseurDto getReferenceById(Integer id) {

        return commandeFournisseurMapper.commandeFournisseurToCommandeFournisseurDto(commandeFournisseurRepository.getReferenceById(id));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {

        return commandeFournisseurMapper.commandeFournisseurToCommandeFournisseurDto(commandeFournisseurRepository.findByCode(code));
    }

    @Override
    public List<CommandeFournisseurDto> findByDateCommandeAfter(Instant dateCommande) {

        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findByDateCommandeAfter(dateCommande);
        List<CommandeFournisseurDto> commandeFournisseursDtos = new ArrayList<>();
        commandeFournisseurs.stream().forEach(commandeFournisseur -> {
            commandeFournisseursDtos.add(
                    commandeFournisseurMapper.commandeFournisseurToCommandeFournisseurDto(
                            commandeFournisseur));
        });
        return commandeFournisseursDtos;
    }

    @Override
    public List<CommandeFournisseurDto> findCommandesFournisseurByIdFournisseur(Integer idFournisseur) {

        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findCommandesFournisseurByFournisseurId(idFournisseur);
        List<CommandeFournisseurDto> commandeFournisseursDtos = new ArrayList<>();
        commandeFournisseurs.stream().forEach(commandeFournisseur -> {
            commandeFournisseursDtos.add(
                    commandeFournisseurMapper.commandeFournisseurToCommandeFournisseurDto(
                            commandeFournisseur));
        });
        return commandeFournisseursDtos;
    }
}
