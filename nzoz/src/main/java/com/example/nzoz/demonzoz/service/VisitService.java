package com.example.nzoz.demonzoz.service;

import com.example.nzoz.demonzoz.dto.DoctorSlotDto;
import java.util.List;

public interface VisitService {
  List<DoctorSlotDto> getVisits(String cityName, String specialty);
}
