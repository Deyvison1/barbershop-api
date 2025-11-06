package com.api.barbershop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.barbershop.model.Specialty;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, UUID> {
	
	boolean existsByNameAndBarberId(String name, UUID id);

}
