package pl.xsolve.commons.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Test {
  private List<Test2> results;
  private Integer count;
}


