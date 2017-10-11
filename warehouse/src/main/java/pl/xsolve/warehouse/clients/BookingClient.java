package pl.xsolve.warehouse.clients;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@Component
@AllArgsConstructor
public class BookingClient {

  protected RabbitTemplate rabbitTemplate;
  protected Exchange exchange;
  protected RestTemplate restTemplate;

  public BookingResponseDto bookViaAMQP(BookingDataDto bookingDataDto) {
    return (BookingResponseDto) rabbitTemplate.convertSendAndReceive(exchange.getName(),"rpc", bookingDataDto);
  }

  public BookingResponseDto bookViaRest(BookingDataDto bookingDataDto) {
    ResponseEntity<BookingResponseDto> bookingResponseDtoResponseEntity =
        restTemplate.postForEntity("http://booking-service/book", bookingDataDto, BookingResponseDto.class, Maps.newConcurrentMap());

    return bookingResponseDtoResponseEntity.getBody();
  }

}
