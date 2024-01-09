package com.isaguler.timefold.controller;

import com.isaguler.timefold.dto.NewAppointmentRequest;
import com.isaguler.timefold.dto.Schedule;
import com.isaguler.timefold.service.TimefoldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TimefoldController {

    private final TimefoldService timefoldService;

    public TimefoldController(TimefoldService timefoldService) {
        this.timefoldService = timefoldService;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newAppointment(@RequestBody NewAppointmentRequest newAppointmentRequest) {
        timefoldService.newAppointment(newAppointmentRequest);

        return new ResponseEntity<>("response", HttpStatus.OK);
    }

    @GetMapping("/schedule")
    public ResponseEntity<Object> schedule() {
        try {
            Schedule schedule = timefoldService.solver();
            return new ResponseEntity<>(schedule, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
}
