package com.isaguler.jobrunr_demo.jobs;

import com.isaguler.jobrunr_demo.repository.PersonRepository;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PersonCountJob {

    private static final Logger log = new JobRunrDashboardLogger(LoggerFactory.getLogger(PersonCountJob.class));

    private final PersonRepository personRepository;

    public PersonCountJob(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @SuppressWarnings("unused")
    @Recurring(id = "person-count", interval = "PT20S", zoneId = "UTC")
    @Job(name = "PersonCountJob", retries = 1)
    public void test() {
        log.atInfo().setMessage("begin at : {}").addArgument(Instant.now()).log();

        log.atInfo().setMessage("Current person count is : {}").addArgument(personRepository.count()).log();

        log.atInfo().setMessage("ended at {}").addArgument(Instant.now()).log();
    }
}
