package ru.sitronics.tn.prodcalendar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getRequiredDate(@RequestParam String date, @RequestParam int count) {
        return ResponseEntity.ok(holidayService.getRequiredDate(date, count));
    }

    @PostMapping("/save")
    @Operation(summary = "Save input holiday date", responses = {
            @ApiResponse(responseCode = "201", description = "Holiday is saved")})
    public ResponseEntity<?> save(@Parameter(description = "Holiday date in ISO format <i>yyyy-MM-dd'T'HH:mm:ss.SSSXXX</i>") @RequestParam String date) {
        holidayService.save(date);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/save-holidays")
    public ResponseEntity<String> saveAll(@RequestBody List<Holiday> holidays) {
        holidayService.saveAll(holidays);
        return ResponseEntity.created(URI.create("/save-holidays")).build();
    }
}