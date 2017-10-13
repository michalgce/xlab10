package software.xsolve.springcloud.scanmed.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.xsolve.commons.dtos.DoctorSlot;
import software.xsolve.springcloud.scanmed.service.ScanmedService;

@RestController
@AllArgsConstructor
public class ScanmedController {

	protected ScanmedService scanmedService;

	@GetMapping("/city/{location}/specialty/{specialty}")
	public List<DoctorSlot> fetchScanmedResponse(
			@PathVariable String location, @PathVariable  String specialty) throws IOException, InterruptedException {

		return scanmedService.fetchDoctorSlots(location, specialty);
	}

}
