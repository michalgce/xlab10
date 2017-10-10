package pl.xsolve.warehouse.clients;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import pl.xsolve.commons.dtos.booking.BookingDataDto;
import pl.xsolve.commons.dtos.booking.BookingResponseDto;

@Component
public class BookingClient {

  protected RabbitTemplate rabbitTemplate;
  protected Exchange exchange;

  public BookingClient(RabbitTemplate rabbitTemplate, Exchange exchange) {
    this.rabbitTemplate = rabbitTemplate;
    this.exchange = exchange;
  }

  public BookingResponseDto bookViaAMQP(BookingDataDto bookingDataDto) {
    return (BookingResponseDto) rabbitTemplate.convertSendAndReceive(exchange.getName(),
        "rpc",
        bookingDataDto);
  }

  public BookingResponseDto bookViaRest(BookingDataDto bookingDataDto) {
    RestTemplate restTemplate = new RestTemplate();

    return restTemplate.postForObject("http://booking-service/book", bookingDataDto, BookingResponseDto.class,
            Maps.newConcurrentMap());
  }

}
