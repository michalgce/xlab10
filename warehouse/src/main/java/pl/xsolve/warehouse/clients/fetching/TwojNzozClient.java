package pl.xsolve.warehouse.clients.fetching;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import pl.xsolve.commons.dtos.DoctorSlot;

@Component
@AllArgsConstructor
public class TwojNzozClient implements FetchingClient {

  public RestTemplate restTemplate;

  public List<DoctorSlot> fetchSlots(String city, String specialty) {
    DoctorSlot[] responseFromTwojnzoz = restTemplate
        .getForObject("http://twojnzoz/city/"+city+"/specialty/"+specialty, DoctorSlot[].class, Maps.newConcurrentMap());

    return Arrays.asList(responseFromTwojnzoz);
  }

}
