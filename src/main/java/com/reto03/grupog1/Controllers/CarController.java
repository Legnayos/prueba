package com.reto03.grupog1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.reto03.grupog1.Entities.Car;
import com.reto03.grupog1.Services.CarService;

@RestController
@RequestMapping("/api/Car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAll(){
        return (List<Car>) carService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Car addCar(@RequestBody Car car) {
        return (Car) carService.addCar(car);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Car updCar(@RequestBody Car car){
        return (Car) carService.updCar(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delCar(@PathVariable Integer id){
        carService.delCar(id);
    }
    
    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable Integer id){
        return carService.getCar(id);
    }

    
}
