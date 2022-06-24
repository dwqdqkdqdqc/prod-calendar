package ru.sitronics.tn.prodcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.prodcalendar.model.Calendar;
import ru.sitronics.tn.prodcalendar.repository.CalendarRepository;
import ru.sitronics.tn.prodcalendar.utils.Utils;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public LocalDate getDate(String date, int count) {
        Optional<Calendar> optCalendar = calendarRepository.findById(1);
        return Utils.getProdDate(optCalendar.orElseThrow(), date, count);
    }

    public void save(Calendar calendar) {
        calendarRepository.save(calendar);
    }
}