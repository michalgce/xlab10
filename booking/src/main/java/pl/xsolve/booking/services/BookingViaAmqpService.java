package pl.xsolve.booking.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.xsolve.booking.entities.Booking;
import pl.xsolve.booking.repositories.BookingRepo;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;


@AllArgsConstructor
@Component
@RabbitListener(queues = "xlab.rpc.booking")
public class BookingViaAmqpService implements BookingService {

  private BookingRepo bookingRepo;

  @Override
  @RabbitHandler
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
}
