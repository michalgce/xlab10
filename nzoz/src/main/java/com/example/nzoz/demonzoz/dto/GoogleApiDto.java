package com.example.nzoz.demonzoz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleApiDto {
  private List<Result> results;

  @Data
  @AllArgsConstructor
  public static class Result {
    private Geometry geometry;
  }

  @Data
  @AllArgsConstructor
  public static class Geometry {

    private Location location;

    @Data
    @AllArgsConstructor
    @Builder
    public static class Location {

      private Double lat;
      private Double lng;
    }
  }
}
