package com.codinghub.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codinghub.portfolio.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

	boolean existsByEmail(String email);
	
}
