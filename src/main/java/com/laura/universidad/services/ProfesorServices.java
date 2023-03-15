package com.laura.universidad.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laura.universidad.models.Profesor;

public interface ProfesorServices {

    public Page<Profesor> findAll(Pageable pageable);

    public Profesor findById(String codigo);

    public void insert(Profesor profesor);

    public void update(Profesor profesor);

    public void delete(String codigo);
}
