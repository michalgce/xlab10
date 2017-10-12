package pl.xsolve.warehouse.clients;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.xsolve.commons.dtos.DoctorSlot;

@Component
@AllArgsConstructor
public class TwojNzozClient {

  public RestTemplate restTemplate;

  public List<DoctorSlot> fetchTwojNzozResponse(String city, String specialty) {
    DoctorSlot[] responseFromTwojnzoz = restTemplate
        .getForObject("http://twojnzoz/city/"+city+"/specialty/"+specialty, DoctorSlot[].class, Maps.newConcurrentMap());

    return Arrays.asList(responseFromTwojnzoz);
  }

}
