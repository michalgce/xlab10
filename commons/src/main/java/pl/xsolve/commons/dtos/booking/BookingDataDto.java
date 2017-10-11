package pl.xsolve.commons.dtos.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookingDataDto {
  private String doctorName;
  private String visitDateTime;

}
