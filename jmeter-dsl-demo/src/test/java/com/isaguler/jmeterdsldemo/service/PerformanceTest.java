package com.isaguler.jmeterdsldemo.service;

import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.core.listeners.JtlWriter;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.dashboard.DashboardVisualizer.dashboardVisualizer;

class PerformanceTest {

    @Test
    void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://localhost:8080/hello")
                )
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    void testPerformance_resultsTreeVisualizer() throws IOException {
        testPlan(
                threadGroup(1, 1,
                        httpSampler("http://localhost:8080/hello")
                ),
                resultsTreeVisualizer()
        ).run();
    }

    @Test
    void testPerformance_jtlWriter() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://localhost:8080/hello")
                ),
                jtlWriter("target/jtls/success")
                        .logOnly(JtlWriter.SampleStatus.SUCCESS),
                jtlWriter("target/jtls/error")
                        .logOnly(JtlWriter.SampleStatus.ERROR)
                        .withAllFields(true)
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    void testPerformance_htmlReporter() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://localhost:8080/hello")
                ),
                htmlReporter("reports")
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    void testPerformance_dashboardVisualizer() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup("Group1")
                        .rampToAndHold(2, Duration.ofSeconds(10), Duration.ofSeconds(10))
                        .children(
                                httpSampler("Sample 1", "http://localhost:8080/hello")
                        ),
                threadGroup("Group2")
                        .rampToAndHold(4, Duration.ofSeconds(10), Duration.ofSeconds(20))
                        .children(
                                httpSampler("Sample 2", "http://localhost:8080/hello")
                        ),
                dashboardVisualizer()
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    @Test
    void testPerformance_responseAssertion() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://localhost:8080/hello")
                                .children(
                                        responseAssertion().containsSubstrings("OK")
                                )
                )
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

}