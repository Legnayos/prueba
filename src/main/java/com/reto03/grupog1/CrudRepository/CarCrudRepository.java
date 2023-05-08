package com.reto03.grupog1.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog1.Entities.Car;

public interface CarCrudRepository extends CrudRepository<Car, Integer> {
    
}
