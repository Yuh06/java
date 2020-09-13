package vn.t3h.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.t3h.model.Pages;

@Repository("pagesDao")
@Transactional
public class PagesDao {
	
	private Logger log = LoggerFactory.getLogger(PagesDao.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	public Pages findById(int id) {
		log.info("id: {}", id);
		try {
			return entityManager.find(Pages.class, id);
		} catch (RuntimeException e) {
			log.error("loi roi: {}", e.getMessage());
			return null;
		}
	}
	
	public void persist(Pages page) {
		try {
			entityManager.persist(page);
		} catch (RuntimeException e) {
			log.error("loi persistb roi: {}", e.getMessage());
			throw e;
		}
	}
	
	public void remove(Pages page) {
		try {
			entityManager.remove(page);
		} catch (RuntimeException e) {
			log.error("loi remove roi: {}", e.getMessage());
			throw e;
		}
	}
	
	public Pages merge(Pages page) {
		try {
			entityManager.merge(page);
			return page;
		} catch (RuntimeException e) {
			log.error("loi merge roi: {}", e.getMessage());
			throw e;
		}
	}
	
	public List<Pages> filter(Pages filter) {
		var et = EntityQuery.create(entityManager, Pages.class);
		return et.like("name", filter.getName())
			.integerEqualsTo("cate", filter.getCate())
			.list();
	}
}
