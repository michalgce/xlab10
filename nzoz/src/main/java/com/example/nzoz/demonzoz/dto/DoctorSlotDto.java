package com.example.nzoz.demonzoz.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DoctorSlotDto {
  private String name;
  private String address;
  private LocalDateTime timeSlot;
}
