package com.service;

import com.dao.role.RoleDao;
import com.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void addRole(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role getRoleByName(String roleName) {
		return roleDao.getRoleByName(roleName);
	}

	@Override
	public Role getRoleById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);
	}

	@Override
	public void deleteRoleById(Long id) {
		roleDao.deleteById(id);
	}
}
