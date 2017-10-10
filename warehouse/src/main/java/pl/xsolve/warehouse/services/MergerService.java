package pl.xsolve.warehouse.services;

import java.util.List;
import org.springframework.stereotype.Service;

import pl.xsolve.commons.dtos.HealthyDataDto;
import pl.xsolve.warehouse.clients.ClientOne;
import pl.xsolve.warehouse.clients.ClientTwo;

@Service
public class MergerService {

  protected ClientOne clientOne;
  protected ClientTwo clientTwo;

  public MergerService(ClientOne clientOne, ClientTwo clientTwo) {
    this.clientOne = clientOne;
    this.clientTwo = clientTwo;
  }

  public List<HealthyDataDto> getData(String city, String speciality) {
    return clientOne.getData(city, speciality);
  }
}
