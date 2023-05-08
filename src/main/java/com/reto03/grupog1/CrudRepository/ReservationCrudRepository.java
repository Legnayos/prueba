package com.reto03.grupog1.CrudRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog1.Entities.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
 
    @Query(value="select count(*) from Reservations where status = ? ", nativeQuery = true)
    public Integer CountByStatus(String status);

    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    @Query("SELECT r.client, COUNT(r.client) \n" + 
            "from Reservation r \n" + 
            "group by r.client \n" + 
            "order by COUNT(r.client) DESC")
    public List<Object[]> countTotalReservationsByClient();
}
