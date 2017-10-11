package pl.xsolve.warehouse.services;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xsolve.commons.dtos.DoctorSlot;
import pl.xsolve.warehouse.clients.ScanmedClient;

@Service
@AllArgsConstructor
public class MergerService {

  protected ScanmedClient scanmedClient;
  //protected KamilClient client;

  public List<DoctorSlot> getDoctorsSlots(String location, String specialty) {
    List<DoctorSlot> scanmedResponse = scanmedClient.fetchScanmedResponse(location, specialty);
    List<DoctorSlot> kamilResponse = Lists.newArrayList();
    scanmedResponse.addAll(kamilResponse);

    return scanmedResponse;
  }

}
