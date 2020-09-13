package vn.t3h.dao;

import java.util.List;

import vn.t3h.model.Category;

public interface CategoryDao {
	public Category create(Category ct);
	public Category update(Category ct);
	public void delete(Category ct);
	public List<Category> allCategory();
}