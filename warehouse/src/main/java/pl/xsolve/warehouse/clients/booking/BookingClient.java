package pl.xsolve.warehouse.clients.booking;

import java.util.List;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableList;

import lombok.AllArgsConstructor;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@Component
@AllArgsConstructor
public class BookingClient {

  protected RabbitTemplate rabbitTemplate;
  protected Exchange exchange;
  protected RestTemplate restTemplate;

  public BookingResponseDto bookViaAMQP(BookingDataDto bookingDataDto) {
    return BookingResponseDto.builder()
            .doctorName("Doc1")
            .visitDateTime("Time1")
            .build();
    //uzupełnij w części #2
  }

  public BookingResponseDto bookViaRest(BookingDataDto bookingDataDto) {
    return BookingResponseDto.builder()
            .doctorName("Doc2")
            .visitDateTime("już w 2021")
            .build();
    //uzupełnij w części #2
  }

  public List<BookingResponseDto> getBookings() {
    return ImmutableList.<BookingResponseDto>builder()
            .add(BookingResponseDto.builder()
                    .doctorName("Doktor Judym")
                    .visitDateTime("za 5 min")
                    .build())
            .add(BookingResponseDto.builder()
                    .doctorName("DoctorQueen")
                    .visitDateTime("już w 2021")
                    .build())
            .build();
    //uzupełnij w części #2
  }
}
