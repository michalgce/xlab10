package pl.xsolve.booking.services;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.xsolve.booking.entities.Booking;
import pl.xsolve.booking.repositories.BookingRepo;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@Component
@AllArgsConstructor
public class BookingViaRestService implements BookingService {

  private BookingRepo bookingRepo;

  public BookingResponseDto bookVisit(BookingDataDto bookingDataDto) {
    Booking bookingEntity = Booking.builder().doctorName(bookingDataDto.getDoctorName())
        .visitDateTime(bookingDataDto.getVisitDateTime()).build();

    Booking savedBooking = bookingRepo.save(bookingEntity);

    return BookingResponseDto.builder().id(savedBooking.getId())
                                       .visitDateTime(bookingDataDto.getVisitDateTime())
                                       .doctorName(bookingDataDto.getDoctorName())
                                       .successful(true)
                                       .build();
  }

  public List<BookingResponseDto> getBookings() {
    return Lists.newArrayList(bookingRepo.findAll()).stream().map(booking -> BookingResponseDto.builder()
        .id(booking.getId())
        .doctorName(booking.getDoctorName())
        .visitDateTime(booking.getVisitDateTime())
        .successful(true).build()).collect(Collectors.toList());
  }
}
