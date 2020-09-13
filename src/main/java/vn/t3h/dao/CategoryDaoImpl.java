package vn.t3h.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import vn.t3h.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Category create(Category ct) {
		entityManager.persist(ct);
		return ct;
	}

	@Override
	public Category update(Category ct) {
		entityManager.merge(ct);
		return ct;
	}

	@Override
	public void delete(Category ct) {
		entityManager.remove(ct);
	}
	
	public List<Category> allCategory() {
		return EntityQuery.create(entityManager, Category.class).list();
	}
}
