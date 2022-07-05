package ru.sitronics.tn.prodcalendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sitronics.tn.prodcalendar.service.DateService;
import ru.sitronics.tn.prodcalendar.utils.exception.NotFoundException;

@RequiredArgsConstructor
@RestController
public class DateController {

    private final DateService dateService;

    @GetMapping("/get")
    public ResponseEntity<?> getDate(@RequestParam int year, @RequestParam int month, @RequestParam int day, @RequestParam int count) {
        try {
            return ResponseEntity.ok(dateService.getDate(year, month, day, count));
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save() {
        //...
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        //...
        return ResponseEntity.noContent().build();
    }
}