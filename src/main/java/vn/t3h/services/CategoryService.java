package vn.t3h.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.t3h.dao.CategoryDao;
import vn.t3h.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getAllCategory() {
		return categoryDao.allCategory();
	}
	
	private List<Category> resultIndent = new ArrayList<Category>();
	public List<Category> arrayWithIndent(List<Category> listCategory, int parentId, String indent) {
		for(Category category : listCategory) {
			if(category.getParentId().equals(parentId)) {
				var tmpCate = new Category();
				tmpCate.setName(indent + " " + category.getName());
				tmpCate.setId(category.getId());
				resultIndent.add(tmpCate);
				this.arrayWithIndent(listCategory, category.getId(), "-");
			}
		}
		return resultIndent;
	}
	
	/*
	 * Điện thoại
	 * - Iphone
	 * -- Iphone X
	 * -- Iphone XS
	 * - SamSung
	 * -- Galaxy
	 * */
	public List<Category> categoryWithIndent() {
		if(resultIndent.size() > 0)
			return resultIndent;
		return arrayWithIndent(getAllCategory(), 0, "");
	}
	
	public void clear () {
		resultIndent.clear();
	}
}
