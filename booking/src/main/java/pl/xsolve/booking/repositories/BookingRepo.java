package pl.xsolve.booking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.xsolve.booking.entities.Booking;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

}
