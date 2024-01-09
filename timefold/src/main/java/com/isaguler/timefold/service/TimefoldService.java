package com.isaguler.timefold.service;

import ai.timefold.solver.core.api.solver.SolverManager;
import com.isaguler.timefold.dto.NewAppointmentRequest;
import com.isaguler.timefold.dto.Schedule;
import com.isaguler.timefold.entity.Appointment;
import com.isaguler.timefold.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.isaguler.timefold.configuration.Bootstrap.availableHoursForDay;

@Service
public class TimefoldService {

    private final AppointmentRepository appointmentRepository;
    private final SolverManager<Schedule, String> solverManager;

    public TimefoldService(AppointmentRepository appointmentRepository, SolverManager<Schedule, String> solverManager) {
        this.appointmentRepository = appointmentRepository;
        this.solverManager = solverManager;
    }

    public void newAppointment(NewAppointmentRequest newAppointmentRequest) {

        Appointment appointment = new Appointment(newAppointmentRequest.title(), newAppointmentRequest.duration());

        appointmentRepository.save(appointment);
    }

    public Schedule solver() throws ExecutionException, InterruptedException {
        List<Appointment> appointmentList = appointmentRepository.findAll();

        Schedule schedule = new Schedule(appointmentList, availableHoursForDay);

        return solverManager.solve("job-1", schedule).getFinalBestSolution();
    }
}
