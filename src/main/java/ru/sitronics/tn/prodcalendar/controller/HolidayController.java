package ru.sitronics.tn.prodcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sitronics.tn.prodcalendar.exception.NotFoundException;
import ru.sitronics.tn.prodcalendar.model.Holiday;
import ru.sitronics.tn.prodcalendar.service.HolidayService;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("/get")
    public ResponseEntity<?> getRequiredDate(@RequestParam String input, @RequestParam int count) {
        try {
            return ResponseEntity.ok(holidayService.getRequiredDate(input, count));
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam String input) {
        holidayService.save(input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/save-holidays")
    public ResponseEntity<String> saveAll(@RequestBody List<Holiday> holidays) {
        holidayService.saveAll(holidays);
        return ResponseEntity.created(URI.create("/save-holidays")).build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        //...
        return ResponseEntity.noContent().build();
    }
}