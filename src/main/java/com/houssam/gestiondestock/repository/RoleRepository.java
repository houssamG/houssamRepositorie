package com.houssam.gestiondestock.repository;

import com.houssam.gestiondestock.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role save(Role article);

    Role getReferenceById(Integer id);

    Role findByNom(String nom);

    @Query(value = "select r from Role r inner join " +
            "r.utilisateur u on u.id =:idUtilisateur")
    List<Role> findRolesByUtilisateur(Integer idUtilisateur);
}
