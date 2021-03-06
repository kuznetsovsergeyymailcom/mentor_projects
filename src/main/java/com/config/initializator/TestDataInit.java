package com.config.initializator;


import com.model.Role;
import com.model.User;
import com.service.RoleService;
import com.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class TestDataInit {

	private UserService userService;
	private RoleService roleService;

	public TestDataInit(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	private void init() throws Exception {

		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");
		roleService.addRole(roleAdmin);

		Role roleUser = new Role();
		roleUser.setName("USER");
		roleService.addRole(roleUser);

		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("admin");
		admin.setName("uncle admin");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(roleAdmin);
		adminRoles.add(roleUser);
		admin.setRoles(adminRoles);

		userService.addUser(admin);

		User user = new User();
		user.setLogin("user");
		user.setPassword("user");
		user.setName("uncle user");
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(roleUser);
		user.setRoles(userRoles);

		userService.addUser(user);

	}
}
