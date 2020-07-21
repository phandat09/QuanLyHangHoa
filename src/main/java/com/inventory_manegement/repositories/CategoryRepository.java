package com.inventory_manegement.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory_manegement.entities.Category;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
