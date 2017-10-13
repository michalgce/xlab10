package pl.xsolve.booking.controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.booking.services.BookingViaRestService;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@RestController
@AllArgsConstructor
public class BookingController {

  private BookingViaRestService bookingViaRestService;

  @GetMapping(value = "bookings")
  public List<BookingResponseDto> getAllBookings() {
    return bookingViaRestService.getBookings();
  }

  @PostMapping(value = "bookings")
  public BookingResponseDto bookVisitViaRest(@RequestBody BookingDataDto booking) {
    return bookingViaRestService.bookVisit(booking);
  }
}
