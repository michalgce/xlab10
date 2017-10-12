package com.example.nzoz.demonzoz.service;

import com.example.nzoz.demonzoz.dto.AvailableVisitsDto;
import com.example.nzoz.demonzoz.dto.AvailableVisitsDto.Day;
import com.example.nzoz.demonzoz.dto.AvailableVisitsDto.Day.Slot;
import com.example.nzoz.demonzoz.dto.AvailableVisitsDto.Day.Slot.Time;
import com.example.nzoz.demonzoz.dto.DoctorSlotDto;
import com.example.nzoz.demonzoz.dto.GoogleApiDto.Geometry.Location;
import com.example.nzoz.demonzoz.util.SpecialityMap;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AdvertService {

  private final RestTemplate restTemplate;
  private final CityNameResolverService cityNameResolverService;

  public List<DoctorSlotDto> getAdvertsTest(final String cityName, final String speciality) {
    Location locationResolved = cityNameResolverService.resolveCityName(cityName);
    String specialityResolved = resolveSpeciality(speciality);
    Document document = getDocumentByLocationAndSpeciality(locationResolved, specialityResolved);

    List<DoctorSlotDto> timeSlots = Lists.newArrayList();
    if (isTitleTerminPicking(document)) {
      DoctorSlotDto doctorSlotDto = resolveOnlyOneResult(document);
      timeSlots.add(doctorSlotDto);
    } else if (isTitleDoctorPicking(document)) {
      List<DoctorSlotDto> doctorSlotDtos = resolveMultipleDoctorsResult(document);
      timeSlots.addAll(doctorSlotDtos);
    }

    return timeSlots;
  }

  private LocalDateTime scrapTimeSlot(final String slotsUrl) {
    AvailableVisitsDto availableVisitsDto = restTemplate.getForObject(slotsUrl,
        AvailableVisitsDto.class);
    Time hour = getHour(availableVisitsDto);

    String dateFromUrl = slotsUrl.substring(slotsUrl.lastIndexOf("/") + 1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(dateFromUrl, formatter);

    Integer hours = hour.getHours();
    Integer minutes = hour.getMinutes();

    return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
        hours, minutes);
  }

  private Time getHour(final AvailableVisitsDto availableVisitsDto) {
    Optional<Day> dayOptional = availableVisitsDto.getDays()
        .stream()
        .filter(Day::isHasSlots)
        .findFirst();
    Day day = dayOptional.orElse(new Day(1, false, Lists.newArrayList()));
    return day.getSlots()
        .stream()
        .filter(
            Objects::nonNull)
        .findFirst()
        .orElse(new Slot(new Time(10, 15), 0, false))
        .getTime();
  }

  private String scrapAddress(final Element element) {
    String street = element.getElementsByClass("street-address")
        .text();
    String postal = element.getElementsByClass("postal-code")
        .text();
    String city = element.getElementsByClass("locality")
        .text();
    return street + " " + postal + " " + city;
  }


  private String scrapDoctor(final Element element) {
    String title = element.getElementsByClass("honorific-suffix")
        .text();
    String name = element.getElementsByClass("given-name")
        .text();
    String surname = element.getElementsByClass("family-name")
        .text();
    return title + " " + name + " " + surname;
  }

  private List<DoctorSlotDto> resolveMultipleDoctorsResult(final Document document) {
    List<DoctorSlotDto> timeSlots = Lists.newArrayList();
    Elements articles = document.getElementsByClass("search-result vcard n");
    articles.forEach(article -> {
      String name = scrapDoctor(article);
      String address = scrapAddress(article);

      String doctorSlotsUrl = parseDoctorUrlToSlotsUrl(article.getElementsByTag("a")
          .attr("href"), 16);
      LocalDateTime timeSlot = scrapTimeSlot(doctorSlotsUrl);

      timeSlots.add(DoctorSlotDto.builder()
          .name(name)
          .address(address)
          .timeSlot(timeSlot)
          .build());
    });
    return timeSlots;
  }

  private DoctorSlotDto resolveOnlyOneResult(final Document document) {
    Elements articles = document.getElementsByClass("selected no-choice");
    Element element = articles.get(1);

    String name = element.getElementsByClass("fn")
        .text();
    String address = scrapAddress(element);

    String doctorSlotsUrl = parseDoctorUrlToSlotsUrl(document.location(), 39);
    LocalDateTime timeSlot = scrapTimeSlot(doctorSlotsUrl);

    return DoctorSlotDto.builder()
        .address(address)
        .name(name)
        .timeSlot(timeSlot)
        .build();
  }

  private String parseDoctorUrlToSlotsUrl(final String url, final Integer firstSingsToRemove) {
    String slotsUrl = extendUrlIfWithoutDomain(url);
    String slotsDate = retrieveDateFromSlotsView(slotsUrl);

    String subUrl = url.substring(firstSingsToRemove);
    Integer clinicId = Integer.valueOf(subUrl.substring(0, subUrl.indexOf("/")));
    Integer doctorId = Integer.valueOf(
        subUrl.substring(subUrl.indexOf("/") + 1, subUrl.indexOf("?A")));
    return "https://www.twojnzoz.pl/wyszukiwarka/3/terminy/" + clinicId + "/" + doctorId
        + "/" + slotsDate;
  }

  private String extendUrlIfWithoutDomain(final String url) {
    String slotsUrl = url;
    if (!url.contains("twojnzoz.pl")) {
      slotsUrl = "https://www.twojnzoz.pl" + url;
    }
    return slotsUrl;
  }

  private String retrieveDateFromSlotsView(String url) {
    Document document = null;
    try {
      document = Jsoup.connect(url)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return document.getElementsByClass("weekPicker")
        .attr("value");

  }

  private boolean isTitleTerminPicking(final Document document) {
    return document.getElementsByTag("title")
        .first()
        .text()
        .contains("Wybór terminu");
  }

  private boolean isTitleDoctorPicking(final Document document) {
    return document.getElementsByTag("title")
        .first()
        .text()
        .contains("Wybór lekarza");
  }

  private Document getDocumentByLocationAndSpeciality(final Location location,
      final String speciality) {
    String web;
    Document document = null;
    try {
      web = "https://www.twojnzoz.pl/wyszukiwarka/2/0?Localization=" + location.getLat() + "%2C"
          + location.getLng() + "&Specialization=" + URLEncoder.encode(speciality, "UTF-8");
      document = Jsoup.connect(web)
          .get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return document;
  }

  private String resolveSpeciality(final String speciality) {
    Optional<String> resolvedSpecialityOptional = Optional.ofNullable(
        SpecialityMap.SPECIALITY_MAP.get(speciality));
    return resolvedSpecialityOptional.orElse("Nobody expects the spanish inquisition");
  }
}
