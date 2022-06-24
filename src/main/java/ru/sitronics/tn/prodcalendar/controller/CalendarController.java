package ru.sitronics.tn.prodcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sitronics.tn.prodcalendar.service.CalendarService;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/find")
    public ResponseEntity<LocalDate> getDate(@RequestParam String date, @RequestParam int count) {
        return ResponseEntity.ok(calendarService.getDate(date, count));
    }
}