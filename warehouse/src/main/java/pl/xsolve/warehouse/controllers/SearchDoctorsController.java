package pl.xsolve.warehouse.controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.commons.dtos.DoctorSlot;
import pl.xsolve.warehouse.services.MergerService;

@RestController
@CrossOrigin
@AllArgsConstructor
public class SearchDoctorsController {

  private MergerService mergerService;

  @GetMapping(value = "/city/{location}/specialty/{specialty}")
  public List<DoctorSlot> searchByCityAndSpeciality(@PathVariable String location, @PathVariable String specialty) {
      return mergerService.getDoctorsSlots(location, specialty);
  }
}
