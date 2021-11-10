package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return jobService.showJobDetails(idJob);
    }

    @PostMapping("")
    @Secured("ROLE_OWNER")
    public Job createJob(@RequestBody Job job) {
        return jobService.createStudent(job);
    }




}
