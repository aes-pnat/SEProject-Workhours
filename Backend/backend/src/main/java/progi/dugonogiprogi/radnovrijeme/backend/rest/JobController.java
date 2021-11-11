package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("")
    public List<Job> listAllJobs() {
        return jobService.listAll();
    }

    @GetMapping("/details")
    public String showWorkDetails(@RequestParam Long idJob) {
        return jobService.showJobDescription(idJob);
    }

    @PostMapping("")
    @Secured("ROLE_OWNER")
    public Job createJob(@RequestBody CreateJobDTO jobDTO) {
        return jobService.createJob(jobDTO.getJobName(), jobDTO.getJobDescription());
    }

}
