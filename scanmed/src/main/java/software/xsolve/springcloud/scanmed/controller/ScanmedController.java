package software.xsolve.springcloud.scanmed.controller;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.xsolve.commons.dtos.DoctorSlot;
import software.xsolve.springcloud.scanmed.service.LocalDateTimeConverter;

@RestController
public class ScanmedController {

	@Autowired
	LocalDateTimeConverter localDateTimeConverter;

	@GetMapping("/city/{location}/specialty/{specialty}")
	public List<DoctorSlot> fetchScanmedResponse(
			@PathVariable String location, @PathVariable  String specialty) throws IOException, InterruptedException {

		try (final WebClient webClient = new WebClient()) {
			configureWebClient(webClient);

			final HtmlPage page = webClient.getPage(
					"https://www.e-scanmed.pl/ron-www/placowkaMedyczna/znajdz?searchInput=" + specialty + " " + location);

			webClient.waitForBackgroundJavaScript(10000);

			return parseResults(page);
		}
		catch (FailingHttpStatusCodeException exception) {
			// one of page elements being fetched with ajax is not available - we don't mind
		}

		return ImmutableList.<DoctorSlot>builder().build();
	}

	private List<DoctorSlot>  parseResults(HtmlPage page) {
		List<DoctorSlot> slots = new ArrayList<>();
		DomNodeList<DomNode> resultNodes = page.querySelectorAll(".listTable");

		for (DomNode resultNode : resultNodes) {
			parseSlot(resultNode).ifPresent(slots::add);
		}
		return slots;
	}

	private Optional<DoctorSlot> parseSlot(DomNode resultNode) {
		DomNode nameNode = resultNode.querySelector(".c-i-name");
		DomNode dateNode = resultNode.querySelector(".c-i-date");
		DomNode timeNode = resultNode.querySelector(".c-i-time");
		DomNode addressNode = resultNode.querySelector(".ltNazwa a");

		if (dateNode != null && timeNode != null && addressNode != null) {
			return Optional.of(DoctorSlot.builder()
					.name(nameNode.asText())
					.address(addressNode.asText())
					.timeSlot(localDateTimeConverter.convert(dateNode.asText(), timeNode.asText())).build());
		} else {
			return Optional.empty();
		}
	}

	private void configureWebClient(WebClient webClient) {
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}
}
