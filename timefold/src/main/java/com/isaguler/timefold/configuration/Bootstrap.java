package com.isaguler.timefold.configuration;

import com.isaguler.timefold.entity.Appointment;
import com.isaguler.timefold.repository.AppointmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Bootstrap {

    private final AppointmentRepository appointmentRepository;

    public Bootstrap(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public static List<LocalTime> availableHoursForDay = new ArrayList<>();

    @PostConstruct
    void init() {
        Appointment a1 = new Appointment("doctor", Duration.ofHours(1));
        Appointment a2 = new Appointment("pub", Duration.ofHours(4));
        Appointment a3 = new Appointment("berber", Duration.ofMinutes(70));

        List<Appointment> appointments = List.of(a1, a2, a3);

        appointmentRepository.saveAll(appointments);

        availableHoursForDay = List.of(
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0)
        );

    }
}
