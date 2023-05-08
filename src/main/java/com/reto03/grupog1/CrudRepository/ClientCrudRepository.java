package com.reto03.grupog1.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog1.Entities.Client;

public interface ClientCrudRepository extends CrudRepository<Client, Integer> {
    
}
