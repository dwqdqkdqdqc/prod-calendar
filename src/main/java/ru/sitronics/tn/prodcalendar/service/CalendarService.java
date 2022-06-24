package ru.sitronics.tn.prodcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.prodcalendar.model.Calendar;
import ru.sitronics.tn.prodcalendar.repository.CalendarRepository;
import ru.sitronics.tn.prodcalendar.utils.Utils;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Transactional
    public LocalDate getDate(String date, int count) {
        Optional<Calendar> optCalendar = calendarRepository.findById(1);

        System.out.println("from service");
        System.out.println(optCalendar);

        return Utils.getProdDate(optCalendar.orElseThrow(), date, count);
    }


//    public static void main(String[] args) {
//        getProdDate(new Calendar("2022", List.of(
//                new MonthOfYear(1, "1,2,3,4,5,6,7,8,9,15,16,22,23,29,30"),
//                new MonthOfYear(2, "5,6,12,13,19,20,22*,23,26,27"),
//                new MonthOfYear(3, "5*,6,7+,8,12,13,19,20,26,27"),
//                new MonthOfYear(4, "2,3,9,10,16,17,23,24,30"),
//                new MonthOfYear(5, "1,2+,3+,7,8,9,10+,14,15,21,22,28,29"),
//                new MonthOfYear(6, "4,5,11,12,13+,18,19,25,26"),
//                new MonthOfYear(7, "2,3,9,10,16,17,23,24,30,31"),
//                new MonthOfYear(8, "6,7,13,14,20,21,27,28"),
//                new MonthOfYear(9, "3,4,10,11,17,18,24,25"),
//                new MonthOfYear(10, "1,2,8,9,15,16,22,23,29,30"),
//                new MonthOfYear(11, "3*,4,5,6,12,13,19,20,26,27"),
//                new MonthOfYear(12, "3,4,10,11,17,18,24,25,31"))), "2022-10-28", 5);
//    }

    public void save(Calendar calendar) {
        calendarRepository.save(calendar);
    }
}