package com.reto03.grupog1.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.DTO.ClientReport;
import com.reto03.grupog1.DTO.StatusReport;
import com.reto03.grupog1.Entities.Client;
import com.reto03.grupog1.Entities.Reservation;
import com.reto03.grupog1.Repository.ReservationRepository;

//import net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy.Simple;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();
    }

    public Reservation addReservation(Reservation reservation) {
        boolean bGrabar = true;

        if(reservation.getStartDate() == null)
            bGrabar = false;

        if(reservation.getDevolutionDate() == null)
            bGrabar = false;
    
        if(reservation.getStatus() == null){
            reservation.setStatus("created");
        }

        if(reservation.getClient().getIdClient() == null)
            bGrabar = false;

        if(reservation.getCar().getIdCar() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return reservationRepository.addReservation(reservation);
        else
            return reservation;
    }

    public Reservation updReservation(Reservation reservation){
        boolean bGrabar = true;

        if(reservation.getIdReservation() == null || reservation.getIdReservation() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return reservationRepository.updReservation(reservation);
        else
            return reservation;
    }

    public void delReservation(Integer id){
        reservationRepository.delReservation(id);
    }

    public Reservation getReservation(Integer id){
        return reservationRepository.getReservation(id);
    }

    public StatusReport getStatusReport(){
        Integer completos = reservationRepository.CountByStatus("completed");
        Integer canceladas = reservationRepository.CountByStatus("cancelled");

        StatusReport statusReport = new StatusReport();
        statusReport.setCancelled(canceladas);
        statusReport.setCompleted(completos);
        
        return statusReport;
    }

    public List<Reservation> getReportReservation(String dateOne, String dateTwo){
        SimpleDateFormat convertidor = new SimpleDateFormat("yyyy-MM-dd");
        Date duno = new Date();
        Date ddos = new Date(); 
        try{
            duno = convertidor.parse(dateOne);
            ddos = convertidor.parse(dateTwo);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (duno.before(ddos)){
            return reservationRepository.getReportReservation(duno, ddos);
        } else
            return new ArrayList<>();
    }

    public List<ClientReport> getTopClientsReport(){

        List<Object[]> objects = reservationRepository.countTotalReservationsByClient();

        List<ClientReport> countClients = new ArrayList<>();

        for (int i=0; i<objects.size(); i++){   
            //Esta es la cantidad
            Long cantidad = (Long) objects.get(i)[1];
            //Este es el cliente
            Client client = (Client) objects.get(i)[0];

            countClients.add(new ClientReport(cantidad,client));
        }

        return countClients;
    }
}

