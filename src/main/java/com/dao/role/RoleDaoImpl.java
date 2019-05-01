package com.dao.role;

import com.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Role getRoleByName(String roleName) {
		TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
		query.setParameter("name", roleName);
		return query.getSingleResult();
	}


	public void save(Role entity) {
		entityManager.persist(entity);
	}

	public Role getById(Long id) {
		return entityManager.find(Role.class, id);
	}

	@Override
	public Role getByName(String name) {
		return this.getRoleByName(name);
	}

	public void update(Role entity) {
		entityManager.merge(entity);
	}

	public void deleteById(Long id) {
		Role entity =  entityManager.find(Role.class, id);
		entityManager.remove(entity);
	}

	public List<Role> getAll() {
		return entityManager.createQuery("FROM Role", Role.class).getResultList();
	}
}
