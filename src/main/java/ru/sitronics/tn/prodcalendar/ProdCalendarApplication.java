package ru.sitronics.tn.prodcalendar;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.MethodInterceptor;
import ru.sitronics.tn.prodcalendar.service.HolidayService;

import javax.sql.DataSource;

@SpringBootApplication
@RequiredArgsConstructor
public class ProdCalendarApplication implements ApplicationRunner {

	private final HolidayService holidayService;

	@Override
	public void run(ApplicationArguments args) {
		holidayService.findHolidays();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdCalendarApplication.class, args);
	}
}