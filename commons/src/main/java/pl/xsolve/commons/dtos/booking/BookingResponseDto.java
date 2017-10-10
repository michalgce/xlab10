package pl.xsolve.commons.dtos.booking;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookingResponseDto {
  private Long id;
  private String doctorName;
  private LocalDateTime visitDateTime;
  private Boolean successful;
}
