package com.autoshop.repo;

import com.autoshop.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobileRepo extends JpaRepository<Automobile, Long> {
    List<Automobile> findAllByCarModel_Id(Long carModelId);
}