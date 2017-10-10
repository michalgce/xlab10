package pl.xsolve.commons.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchQueryDto {

  protected String city;
  protected String speciality;

}
