package pl.xsolve.warehouse.clients;

import java.util.List;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import pl.xsolve.commons.dtos.dtos.HealthyDataDto;
import pl.xsolve.commons.dtos.dtos.SearchQueryDto;

@Component
@RequestScope
public class ClientOne {

  protected RabbitTemplate rabbitTemplate;
  protected Exchange exchange;

  public ClientOne(RabbitTemplate rabbitTemplate, Exchange exchange) {
    this.rabbitTemplate = rabbitTemplate;
    this.exchange = exchange;
  }

  public List<HealthyDataDto> getData(String city, String speciality) {
    Object rpc = rabbitTemplate.convertSendAndReceive(exchange.getName(), "rpc",
        SearchQueryDto.builder().city(city).speciality(speciality).build());

    return (List<HealthyDataDto>) rpc;
  }

}
