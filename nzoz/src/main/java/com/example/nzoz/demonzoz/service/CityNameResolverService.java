package com.example.nzoz.demonzoz.service;

import com.example.nzoz.demonzoz.dto.GoogleApiDto.Geometry.Location;

public interface CityNameResolverService {
  Location resolveCityName(String cityName);
}
