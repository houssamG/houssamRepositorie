package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.RoleDto;
import com.houssam.gestiondestock.mapper.RoleMapper;
import com.houssam.gestiondestock.model.Role;
import com.houssam.gestiondestock.repository.RoleRepository;
import com.houssam.gestiondestock.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RoleServiceImpl")
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {

        return roleMapper.roleToRoleDto(roleRepository.save(roleMapper.roleDtoToRole(roleDto)));
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto getReferenceById(Integer id) {

        return roleMapper.roleToRoleDto(roleRepository.getReferenceById(id));
    }

    @Override
    public RoleDto findByNom(String nom) {

        return roleMapper.roleToRoleDto(roleRepository.findByNom(nom));
    }

    @Override
    public List<RoleDto> findRolesByUtilisateur(Integer idUtilisateur) {

        List<Role> roles = roleRepository.findRolesByUtilisateur(idUtilisateur);
        List<RoleDto> rolesDtos = new ArrayList<>();
        roles.stream().forEach(role -> {
            rolesDtos.add(
                    roleMapper.roleToRoleDto(
                            role));
        });
        return rolesDtos;

    }
}
