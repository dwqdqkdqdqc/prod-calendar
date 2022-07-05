package ru.sitronics.tn.prodcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.prodcalendar.repository.DateRepository;
import ru.sitronics.tn.prodcalendar.utils.exception.NotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DateService {

    private final DateRepository dateRepository;

    private List<LocalDate> daysOff;

    public void findDaysOff() {
        daysOff = dateRepository.findAll()
                .stream()
                .map(date -> LocalDate.of(date.getYear(), date.getMonth(), date.getDay()))
                .toList();
    }

    public LocalDate getDate(int year, int month, int day, int daysCount) {
        if (daysCount <= 0) {
            throw new NotFoundException("Invalid date.");
        }
        List<LocalDate> workDays = LocalDate
                .of(year, month, day)
                .datesUntil(LocalDate.of(year + 1, 1, 1))
                .filter(localDate -> !daysOff.contains(localDate))
                .toList();
        return workDays.get(daysCount);
    }
}