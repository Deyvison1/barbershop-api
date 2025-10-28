package com.api.barbershop.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.barbershop.model.HaircutImage;

@Repository
public interface IHaircutImageRepository extends JpaRepository<HaircutImage, UUID> {
	List<HaircutImage> findByHaircutId(UUID id);

	boolean existsByHaircutIdAndActiveTrue(UUID haircutId);
}
