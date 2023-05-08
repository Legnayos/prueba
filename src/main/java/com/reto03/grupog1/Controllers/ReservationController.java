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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog1.DTO.ClientReport;
import com.reto03.grupog1.DTO.StatusReport;
import com.reto03.grupog1.Entities.Reservation;
import com.reto03.grupog1.Services.ReservationService;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return (Reservation) reservationService.addReservation(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation updReservation(@RequestBody Reservation reservation){
        return (Reservation) reservationService.updReservation(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delReservation(@PathVariable Integer id){
        reservationService.delReservation(id);
    }
    
    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservation(@PathVariable Integer id){
        return reservationService.getReservation(id);
    }

    @GetMapping ("/report-status")
    public StatusReport getStatusReport(){      //el profe cambia esto?
        return reservationService.getStatusReport();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates (@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
        return reservationService.getReportReservation(dateOne, dateTwo);
    }

    @GetMapping ("/report-clients")
    public List<ClientReport> getClientReport(){
        return reservationService.getTopClientsReport();
    }
}
