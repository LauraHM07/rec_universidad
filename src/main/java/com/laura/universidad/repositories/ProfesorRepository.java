package com.laura.universidad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.universidad.models.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, String>{
    
}
