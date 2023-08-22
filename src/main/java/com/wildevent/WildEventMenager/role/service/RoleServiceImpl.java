package com.wildevent.WildEventMenager.role.service;

import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Set<Role> mapRolesFromIds(Set<String> roleIds) {
        return roleIds.stream()
                .map(UUID::fromString)
                .flatMap(uuid -> roleRepository.findById(uuid).stream())
                .collect(Collectors.toSet());
    }
}
