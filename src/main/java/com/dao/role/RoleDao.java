package com.dao.role;


import com.dao.generic.GenericDao;
import com.model.Role;

public interface RoleDao extends GenericDao<Role> {
	Role getRoleByName(String roleName);
}
