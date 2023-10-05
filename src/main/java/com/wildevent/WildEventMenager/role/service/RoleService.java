package com.wildevent.WildEventMenager.role.service;

import com.wildevent.WildEventMenager.role.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> mapRolesFromIds(Set<String> roleIds);
}
