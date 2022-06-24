package ru.sitronics.tn.prodcalendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import ru.sitronics.tn.prodcalendar.model.Calendar;
import ru.sitronics.tn.prodcalendar.service.CalendarService;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ProdCalendarApplication implements ApplicationRunner {

	private final RestTemplate template = new RestTemplateBuilder().build();
	private final String url = "http://xmlcalendar.ru/data/ru/2022/calendar.json";
	private final CalendarService calendarService;

	@Override
	public void run(ApplicationArguments args) {
		String json = template.getForObject(url, String.class);
		log.info("Got json from the net");
		try {
			Calendar calendar = new ObjectMapper().readValue(json, Calendar.class);
			calendarService.save(calendar);
			log.info("Saved the obj to the db");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdCalendarApplication.class, args);
	}
}