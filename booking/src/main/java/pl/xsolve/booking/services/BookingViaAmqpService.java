package pl.xsolve.booking.services;

import pl.xsolve.booking.repositories.BookingRepo;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;


public class BookingViaAmqpService implements BookingService {

  private BookingRepo bookingRepo;

  @Override
  public BookingResponseDto bookVisit(BookingDataDto bookingDataDto) {

  }
}
