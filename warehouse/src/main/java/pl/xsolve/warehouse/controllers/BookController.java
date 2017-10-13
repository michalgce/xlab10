package pl.xsolve.warehouse.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;
import pl.xsolve.warehouse.clients.booking.BookingClient;

@RestController
@AllArgsConstructor
@CrossOrigin
public class BookController {

  private BookingClient bookingClient;

  @PostMapping(value = "rest/book")
  public BookingResponseDto bookVisitRest(@RequestBody BookingDataDto booking) {
    return bookingClient.bookViaRest(booking);
  }

  @PostMapping(value = "amqp/book")
  public BookingResponseDto bookVisitAmqp(@RequestBody BookingDataDto booking) {
    return bookingClient.bookViaAMQP(booking);
  }

  @GetMapping(value = "bookings")
  public List<BookingResponseDto> getAllBookings() {
    return bookingClient.getBookings();
  }

}
