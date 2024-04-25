package com.autoshop.repo;

import com.autoshop.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepo extends JpaRepository<CarModel, Long> {
}