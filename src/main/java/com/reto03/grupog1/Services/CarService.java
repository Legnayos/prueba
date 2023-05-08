package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Car;
import com.reto03.grupog1.Repository.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return (List<Car>) carRepository.getAll();
    }

    public Car addCar(Car car) {
        boolean bGrabar = true;

        if(car.getDescription() == null)
            bGrabar = false;

        if(car.getName() == null)
            bGrabar = false;

        if(car.getModel() == null)
            bGrabar = false;

/*
        if(car.getGama().getIdGama() == null)
            bGrabar = false;
 */

        if(bGrabar ==true)
            return carRepository.addCar(car);
        else
            return car;
    }

    public Car updCar(Car car){
        boolean bGrabar = true;

        if(car.getIdCar() == null || car.getIdCar() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return carRepository.updCar(car);
        else
            return car;
    }

    public void delCar(Integer id){
        carRepository.delCar(id);
    }

    public Car getCar(Integer id){
        return carRepository.getCar(id);
    }
}

