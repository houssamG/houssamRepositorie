package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.RoleDto;
import com.houssam.gestiondestock.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    @Mapping(target = "utilisateur" , ignore = true)
    RoleDto roleToRoleDto(Role role);
    @Mapping(target = "utilisateur" , ignore = true)
    Role roleDtoToRole(RoleDto roleDto);
}
