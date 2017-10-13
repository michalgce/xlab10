package pl.xsolve.warehouse.clients.fetching;

import java.util.List;

import pl.xsolve.commons.dtos.DoctorSlot;

public interface FetchingClient {

	List<DoctorSlot> fetchSlots(String city, String specialty);

}
