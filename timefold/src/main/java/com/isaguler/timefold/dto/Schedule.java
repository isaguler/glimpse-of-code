package com.isaguler.timefold.dto;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import com.isaguler.timefold.entity.Appointment;

import java.time.LocalTime;
import java.util.List;

@PlanningSolution
public class Schedule {

    @PlanningEntityCollectionProperty
    private List<Appointment> appointmentList;

    @ValueRangeProvider
    private List<LocalTime> startTimeList;

    @PlanningScore
    private HardSoftScore hardSoftScore;

    public Schedule() {
    }

    public Schedule(List<Appointment> appointmentList, List<LocalTime> startTimeList) {
        this.appointmentList = appointmentList;
        this.startTimeList = startTimeList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<LocalTime> getStartTimeList() {
        return startTimeList;
    }

    public void setStartTimeList(List<LocalTime> startTimeList) {
        this.startTimeList = startTimeList;
    }

    public HardSoftScore getHardSoftScore() {
        return hardSoftScore;
    }

    public void setHardSoftScore(HardSoftScore hardSoftScore) {
        this.hardSoftScore = hardSoftScore;
    }
}
