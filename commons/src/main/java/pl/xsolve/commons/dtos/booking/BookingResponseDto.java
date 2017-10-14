package pl.xsolve.commons.dtos.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookingResponseDto {
  private Long id;
  private String doctorName;
  private String visitDateTime;
  private Boolean successful;
}
