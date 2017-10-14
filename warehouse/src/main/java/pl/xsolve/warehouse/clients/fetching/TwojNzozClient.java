package pl.xsolve.warehouse.clients.fetching;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableList;

import pl.xsolve.commons.dtos.DoctorSlot;

public class TwojNzozClient implements FetchingClient {

  public RestTemplate restTemplate;

  public List<DoctorSlot> fetchSlots(String city, String specialty) {
    // uzupełnij w części 2
    return ImmutableList.<DoctorSlot>builder()
            .add(DoctorSlot.builder()
                    .name("Doktor Burski")
                    .address("ul. Ojca Mateusza 4")
                    .timeSlot(LocalDateTime.now())
                    .build())
            .add(DoctorSlot.builder()
                    .name("Dr Dre")
                    .address("Hiphpowa 15")
                    .timeSlot(LocalDateTime.now())
                    .build())
            .build();
  }

}
