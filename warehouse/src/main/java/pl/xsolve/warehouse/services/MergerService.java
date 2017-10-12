package pl.xsolve.warehouse.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import pl.xsolve.commons.dtos.DoctorSlot;
import pl.xsolve.warehouse.clients.fetching.FetchingClient;

@Service
public class MergerService {

  private static final Logger logger = LoggerFactory.getLogger(MergerService.class);

  protected List<FetchingClient> apiClients;

  public MergerService(List<FetchingClient> apiClients) {
    this.apiClients = apiClients;
  }

  public List<DoctorSlot> getDoctorsSlots(String location, String specialty) {
    return apiClients.stream()
            .map(client -> fetchLater(location, specialty, client))
            .map(CompletableFuture::join)
            .flatMap(slots -> slots.stream())
            .collect(Collectors.toList());
  }

  protected CompletableFuture<List<DoctorSlot>> fetchLater(String location, String specialty, FetchingClient client) {
    return CompletableFuture.supplyAsync(() -> client.fetchSlots(location, specialty))
			.handle((fetchedSlots, errorFromService) -> {
			  if (errorFromService != null) {
				logger.error("Error while fetching from API: " + client.getClass().getName(), errorFromService);
				return ImmutableList.<DoctorSlot>builder().build();
			  } else {
				return fetchedSlots;
			  }
			});
  }
}
