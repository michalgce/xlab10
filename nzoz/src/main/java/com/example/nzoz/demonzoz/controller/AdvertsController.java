package com.example.nzoz.demonzoz.controller;

import com.example.nzoz.demonzoz.dto.DoctorSlotDto;
import com.example.nzoz.demonzoz.service.AdvertService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdvertsController {

  private final AdvertService advertService;

  @GetMapping("/city/{location}/specialty/{specialty}")
  public List<DoctorSlotDto> getAdverts(@PathVariable final String location,
      @PathVariable final String specialty) {
    return advertService.getAdverts(location, specialty);
  }
}
