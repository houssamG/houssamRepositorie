package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.RoleDto;

import java.util.List;


public interface RoleService {
    RoleDto save(RoleDto roleDto);

    void delete(Integer id);

    RoleDto getReferenceById(Integer id);

    RoleDto findByNom(String nom);

    List<RoleDto> findRolesByUtilisateur(Integer idUtilisateur);
}
