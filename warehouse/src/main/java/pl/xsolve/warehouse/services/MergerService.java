package pl.xsolve.warehouse.services;

import org.springframework.stereotype.Service;
import pl.xsolve.warehouse.clients.BookingClient;
import pl.xsolve.warehouse.clients.ClientTwo;

@Service
public class MergerService {

  protected BookingClient bookingClient;
  protected ClientTwo clientTwo;

  public MergerService(BookingClient bookingClient, ClientTwo clientTwo) {
    this.bookingClient = bookingClient;
    this.clientTwo = clientTwo;
  }

}
