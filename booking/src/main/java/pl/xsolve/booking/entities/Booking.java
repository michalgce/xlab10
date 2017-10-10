package pl.xsolve.booking.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Value;

@Entity
@Value
@Builder
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String doctorName;
  private LocalDateTime visitDateTime;
  private Boolean successful;
}
