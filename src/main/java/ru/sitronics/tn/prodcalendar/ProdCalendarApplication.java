package ru.sitronics.tn.prodcalendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sitronics.tn.prodcalendar.service.DateService;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ProdCalendarApplication implements ApplicationRunner {

	private final DateService dateService;

	@Override
	public void run(ApplicationArguments args) {
		dateService.findDaysOff();
		log.info("Got all the days off from the DB");
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdCalendarApplication.class, args);
	}
}