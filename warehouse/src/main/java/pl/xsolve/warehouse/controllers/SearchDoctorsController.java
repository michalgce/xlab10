package pl.xsolve.warehouse.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.commons.dtos.HealthyDataDto;
import pl.xsolve.warehouse.services.MergerService;

@RestController
public class SearchDoctorsController {

  protected MergerService mergerService;

  public SearchDoctorsController(MergerService mergerService) {
    this.mergerService = mergerService;
  }

  @GetMapping(value = "/city/{city}/speciality/{speciality}")
  public List<HealthyDataDto> searchByCityAndSpeciality(@PathVariable String city, @PathVariable String speciality) {
      return mergerService.getData(city, speciality);

  }

}
