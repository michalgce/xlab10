package pl.xsolve.commons.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DoctorSlot {
	private String name;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timeSlot;

	private String address;
}
