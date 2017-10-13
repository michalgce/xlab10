package pl.xsolve.booking.services;

import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

public interface BookingService {
  BookingResponseDto bookVisit(BookingDataDto bookingDataDto);
}
