package com.reto03.grupog1.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.ReservationCrudRepository;
import com.reto03.grupog1.Entities.Car;
import com.reto03.grupog1.Entities.Client;
import com.reto03.grupog1.Entities.Reservation;

@Repository
public class ReservationRepository {
    @Autowired 
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation){
        if(reservation.getIdReservation() == null || reservation.getIdReservation() == 0)
            return reservationCrudRepository.save(reservation);
        else
            return reservation;
    }

    public Reservation existReservation(Integer Id){

        Optional <Reservation> objReservation = reservationCrudRepository.findById(Id);
        Reservation objReservationRespuesta;

        if(objReservation.isEmpty() == true)
            objReservationRespuesta = null;

        else{
            objReservationRespuesta = objReservation.get();
        }
        
        return objReservationRespuesta;
    }

    public Reservation updReservation(Reservation reservation){
        //boolean bGrabar = true;
        Client client = new Client();
        Car car = new Car();

        Reservation objReservation = existReservation(reservation.getIdReservation());
        if(objReservation == null){
            return reservation;
        } 

        if(reservation.getStartDate() !=  null)
            objReservation.setStartDate(reservation.getStartDate());
            
        if(reservation.getDevolutionDate() != null);
            objReservation.setDevolutionDate(reservation.getDevolutionDate());

        if(reservation.getStatus() != null)
            objReservation.setStatus(reservation.getStatus());

        if(reservation.getClient().getIdClient() != null){
            client.setIdClient(reservation.getClient().getIdClient());
            objReservation.setClient(client);
        }

        if(reservation.getCar().getIdCar() != null){
            car.setIdCar(reservation.getCar().getIdCar());
            objReservation.setCar(car);
        }

        return reservationCrudRepository.save(objReservation);

    }

    public void delReservation(Integer id){
        Reservation objReservation = existReservation(id);
        if(objReservation != null){
            reservationCrudRepository.deleteById(id);
        }
    }

    public Reservation getReservation(Integer id)
    {
        Reservation objReservation = existReservation(id);
        if(objReservation != null)
            return objReservation;
        
        else
            return null;
    }

    public Integer CountByStatus(String status){
        return reservationCrudRepository.CountByStatus(status);
    }

    public List<Reservation> getReportReservation(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<Object[]> countTotalReservationsByClient(){
        return reservationCrudRepository.countTotalReservationsByClient();
    }
}
