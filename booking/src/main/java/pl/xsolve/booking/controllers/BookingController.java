package pl.xsolve.booking.controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.booking.services.BookingService;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@RestController
@AllArgsConstructor
public class BookingController {

  private BookingService bookingService;

  @GetMapping(value = "bookings")
  public List<BookingResponseDto> getAllBookings() {
    return bookingService.getBookings();
  }

  @PostMapping(value = "book")
  public BookingResponseDto bookVisit(@RequestBody BookingDataDto booking) {
    return bookingService.bookVisit(booking);
  }
}
