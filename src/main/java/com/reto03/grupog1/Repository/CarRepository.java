package com.reto03.grupog1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.CarCrudRepository;
import com.reto03.grupog1.Entities.Car;
//import com.reto03.grupog1.Entities.Gama;

@Repository
public class CarRepository {
    @Autowired 
    private CarCrudRepository carCrudRepository;

    public List<Car> getAll(){
        return (List<Car>) carCrudRepository.findAll();
    }

    public Car addCar(Car car){
        if(car.getIdCar() == null || car.getIdCar() == 0)
            return carCrudRepository.save(car);
        else
            return car;
    }

    public Car existCar(Integer Id){

        Optional <Car> objCar = carCrudRepository.findById(Id);
        Car objCarRespuesta;

        if(objCar.isEmpty() == true)
            objCarRespuesta = null;

        else{
            objCarRespuesta = objCar.get();
        }
        
        return objCarRespuesta;
    }

    public Car updCar(Car car){
        //Gama gama = new Gama(); 

        Car objCar = existCar(car.getIdCar());
        if(objCar == null){
            return car;
        } 

        if(car.getBrand() !=  null) 
            objCar.setBrand(car.getBrand());
            
        if(car.getDescription() != null);
            objCar.setDescription(car.getDescription());

        if(car.getName() != null)
            objCar.setName(car.getName());

        if(car.getModel() != null)
            objCar.setModel(car.getModel());

        return carCrudRepository.save(objCar);

    }

    public void delCar(Integer id){
        Car objCar = existCar(id);
        if(objCar != null){
            carCrudRepository.deleteById(id);
        }
    }

    public Car getCar(Integer id)
    {
        Car objCar = existCar(id);
        if(objCar != null)
            return objCar;
        
        else
            return null;
    }
}
