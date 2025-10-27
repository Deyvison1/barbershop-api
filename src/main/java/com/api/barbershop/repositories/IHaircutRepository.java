package com.api.barbershop.repositories;

import com.api.barbershop.models.Haircut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHaircutRepository extends JpaRepository<Haircut, UUID>, JpaSpecificationExecutor<Haircut> {
}
