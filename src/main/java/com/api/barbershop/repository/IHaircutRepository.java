package com.api.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.api.barbershop.model.Haircut;

import java.util.List;
import java.util.UUID;

@Repository
public interface IHaircutRepository extends JpaRepository<Haircut, UUID>, JpaSpecificationExecutor<Haircut> {
	List<Haircut> findAll();
}
