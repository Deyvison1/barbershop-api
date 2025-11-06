package com.api.barbershop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.api.barbershop.model.Barber;

@Repository
public interface IBarberRepository extends JpaRepository<Barber, UUID>, JpaSpecificationExecutor<Barber> {

}
