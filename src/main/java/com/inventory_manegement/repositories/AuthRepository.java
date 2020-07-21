package com.inventory_manegement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory_manegement.entities.Auth;

@Repository
public interface AuthRepository  extends JpaRepository<Auth, Integer>{

}
