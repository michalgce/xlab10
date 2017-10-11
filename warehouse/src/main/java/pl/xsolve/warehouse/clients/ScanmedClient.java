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
public class ScanmedClient {

  public RestTemplate restTemplate;

  public List<DoctorSlot> fetchScanmedResponse(String city, String specialty) {
    DoctorSlot[] responseFromScanmed = restTemplate
        .getForObject("http://scanmed/city/"+city+"/specialty/"+specialty, DoctorSlot[].class, Maps.newConcurrentMap());

    return Arrays.asList(responseFromScanmed);
  }

}
