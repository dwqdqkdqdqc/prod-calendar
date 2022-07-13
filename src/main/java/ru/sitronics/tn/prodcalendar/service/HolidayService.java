package ru.sitronics.tn.prodcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.prodcalendar.exception.NotFoundException;
import ru.sitronics.tn.prodcalendar.model.Holiday;
import ru.sitronics.tn.prodcalendar.repository.HolidayRepository;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;

    private List<LocalDateTime> holidays;

    public void findHolidays() {
        holidays = holidayRepository.findAll()
                .stream()
                .map(Holiday::getDate)
                .toList();
    }

    public LocalDateTime getRequiredDate(String input, int count) {
        if (count <= 0) {
            throw new NotFoundException("Invalid count of days.");
        }
        LocalDateTime workDay = LocalDateTime.parse(input);
        List<LocalDateTime> allWorkDays = Stream
                .iterate(workDay, i -> i.plus(1, ChronoUnit.DAYS))
                .limit(ChronoUnit.DAYS.between(workDay, LocalDateTime.of(workDay.getYear() + 1, Month.JANUARY, 1, 0, 0)))
                .collect(Collectors.toList());
        allWorkDays.removeIf(holidays::contains);
        return allWorkDays.stream().toList().get(count);
    }

    public void save(String input) {
        LocalDateTime localDateTime = LocalDateTime.parse(input);
        holidayRepository.save(Holiday.builder().date(localDateTime).build());
    }

    public void saveAll(List<Holiday> holidays) {
        holidayRepository.saveAllAndFlush(holidays);
    }
}