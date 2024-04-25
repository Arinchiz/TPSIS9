package com.autoshop.repo;

import com.autoshop.model.Application;
import com.autoshop.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
}