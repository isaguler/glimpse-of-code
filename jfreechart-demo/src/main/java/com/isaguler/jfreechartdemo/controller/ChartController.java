package com.isaguler.jfreechartdemo.controller;

import com.isaguler.jfreechartdemo.service.ChartService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping(value = "/monthly", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] monthlyIncomeExpenseChart() {
        return chartService.monthlyIncomeChart();
    }
}
