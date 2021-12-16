package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.CreateJobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.JobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.List;

/**
 * Handles requests for Jobs page.
 */

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("")
    public List<Job> listAllJobs() {
        return jobService.listAll();
    }

    @PostMapping("/add")
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping("/delete")
    public void deleteJob(@RequestParam Integer id) {
        jobService.deleteJob(id);
    }

}
