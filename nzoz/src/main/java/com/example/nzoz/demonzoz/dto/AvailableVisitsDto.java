package com.example.nzoz.demonzoz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailableVisitsDto {
  @JsonProperty(value = "DataOd", access = Access.WRITE_ONLY)
  private String dateFrom;
  @JsonProperty(value = "DataDo", access = Access.WRITE_ONLY)
  private String dateTo;
  @JsonProperty(value = "Days", access = Access.WRITE_ONLY)
  private List<Day> days;

  @Data
  @AllArgsConstructor
  public static class Day {
    @JsonProperty(value = "DayInWeek", access = Access.WRITE_ONLY)
    private Integer dayInWeek;
    @JsonProperty(value = "HasSlots", access = Access.WRITE_ONLY)
    private boolean hasSlots;
    @JsonProperty(value = "Slots", access = Access.WRITE_ONLY)
    private List<Slot> slots;

    @Data
    @AllArgsConstructor
    public static class Slot {

      @JsonProperty(value = "Godzina", access = Access.WRITE_ONLY)
      private Time time;
      @JsonProperty(value = "TerminId", access = Access.WRITE_ONLY)
      private Integer termId;
      @JsonProperty(value = "RozliczaniePrzezNfz", access = Access.WRITE_ONLY)
      private boolean payedByNfz;

      @Data
      @AllArgsConstructor
      public static class Time {
        @JsonProperty(value = "Hours", access = Access.WRITE_ONLY)
        private Integer hours;
        @JsonProperty(value = "Minutes", access = Access.WRITE_ONLY)
        private Integer minutes;
      }
    }
  }
}
