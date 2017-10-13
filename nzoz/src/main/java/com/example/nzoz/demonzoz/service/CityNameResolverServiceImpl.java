package com.example.nzoz.demonzoz.service;

import com.example.nzoz.demonzoz.dto.GoogleApiDto;
import com.example.nzoz.demonzoz.dto.GoogleApiDto.Geometry.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityNameResolverServiceImpl implements CityNameResolverService {

  @Value("${google.maps.api.key}")
  private String GOOGLE_MAPS_API_KEY;

  private RestTemplate restTemplate;

  public CityNameResolverServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Location resolveCityName(final String cityName) {
    GoogleApiDto googleApiDto = restTemplate.getForObject(
        "https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName + ",+PL&key=" + GOOGLE_MAPS_API_KEY, GoogleApiDto.class);

    Location location = googleApiDto.getResults()
        .get(0)
        .getGeometry()
        .getLocation();

    return Location.builder()
        .lat(location.getLat())
        .lng(location.getLng())
        .build();
  }
}
