package pl.xsolve.booking.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.booking.services.BookingViaRestService;

@RestController
@AllArgsConstructor
public class BookingController {

  private BookingViaRestService bookingViaRestService;

}
