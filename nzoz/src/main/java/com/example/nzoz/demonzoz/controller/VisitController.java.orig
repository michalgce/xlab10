package com.example.nzoz.demonzoz.controller;

import com.example.nzoz.demonzoz.dto.DoctorSlotDto;
import com.example.nzoz.demonzoz.service.VisitService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VisitController {

  private final VisitService visitService;

  @GetMapping("/city/{location}/specialty/{specialty}")
  public List<DoctorSlotDto> getVisits(@PathVariable final String location,
      @PathVariable final String specialty) {
    return visitService.getVisits(location, specialty);
  }
}
