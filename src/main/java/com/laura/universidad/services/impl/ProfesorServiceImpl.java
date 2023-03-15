package com.laura.universidad.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laura.universidad.models.Profesor;
import com.laura.universidad.repositories.ProfesorRepository;
import com.laura.universidad.services.ProfesorServices;

@Service
public class ProfesorServiceImpl implements ProfesorServices {
    
    @Autowired
    ProfesorRepository repository;

    @Override
    public Page<Profesor> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Profesor findById(String codigo) {
        Optional<Profesor> findById = repository.findById(codigo);

        if(findById != null) {
            return findById.get();
        }

        return null;
    }

    @Override
    public void insert(Profesor profesor){
        repository.save(profesor);
    }

    @Override
    public void update(Profesor profesor) {
        repository.save(profesor);
    }

    @Override
    public void delete(String codigo){
        repository.deleteById(codigo);
    }
}
