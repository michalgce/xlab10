package pl.xsolve.warehouse.services;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xsolve.commons.dtos.DoctorSlot;
import pl.xsolve.warehouse.clients.ScanmedClient;
import pl.xsolve.warehouse.clients.TwojNzozClient;

@Service
@AllArgsConstructor
public class MergerService {

  protected ScanmedClient scanmedClient;
  protected TwojNzozClient nzozClient;

  public List<DoctorSlot> getDoctorsSlots(String location, String specialty) {
    List<DoctorSlot> slots = Lists.newArrayList();
    List<DoctorSlot> scanmedResponse = scanmedClient.fetchScanmedResponse(location, specialty);
    List<DoctorSlot> twojNzozResponse = nzozClient.fetchTwojNzozResponse(location, specialty);
    slots.addAll(scanmedResponse);
    slots.addAll(twojNzozResponse);

    return slots;
  }

}
