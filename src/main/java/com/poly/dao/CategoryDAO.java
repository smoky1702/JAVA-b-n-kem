package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<Category, String> {
//	@Query(value = "SELECT count(c.id) FROM Categories c", nativeQuery = true)
//	Integer countAllCategory();
}
