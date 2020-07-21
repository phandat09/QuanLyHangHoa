package com.inventory_manegement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory_manegement.entities.Invoke;

@Repository
public interface InvokeRepository extends JpaRepository<Invoke, Integer>{

}
