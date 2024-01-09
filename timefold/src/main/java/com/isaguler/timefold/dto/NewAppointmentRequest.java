package com.isaguler.timefold.dto;

import java.time.Duration;

public record NewAppointmentRequest(String title, Duration duration) {
}
