package pl.xsolve.clientone.amqp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import pl.xsolve.commons.dtos.HealthyDataDto;
import pl.xsolve.commons.dtos.SearchQueryDto;
import pl.xsolve.commons.dtos.Test;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RabbitListener(queues = "xlab.rpc.request")
public class Receiver {

  @RabbitHandler
  public List<HealthyDataDto> getTest(SearchQueryDto queryDto) {
    System.out.println("Incoming time " + DateTime.now());

    RestTemplate restTemplate = new RestTemplate();


    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Mashape-Key", "QrZ4mtIe1VmshOiXg5ku0LtpGmNUp1EQ2qjjsnNDatGDtnNN75");
    httpHeaders.add("User-Agent", "Mozilla/5.0");
    HttpEntity httpEntity = new HttpEntity(httpHeaders);

    ResponseEntity<Test> exchange = restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/?limit=300", HttpMethod.GET, httpEntity, Test.class, Maps.newConcurrentMap());

    return exchange.getBody().getResults().stream()
                                          .map(b -> HealthyDataDto.builder().doctorName(b.getName()).build())
                                          .collect(Collectors.toList());
  }
}
