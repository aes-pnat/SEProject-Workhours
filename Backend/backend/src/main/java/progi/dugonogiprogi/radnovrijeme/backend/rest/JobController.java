package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    /**
     * Handles HTTP GET request for listing all the jobs.
     *
     * @return a list of jobs
     */

    @GetMapping("")
    public List<JobDTO> listAllJobs() {
        return jobService.listAll();
    }

    /**
     * Handles HTTP GET request for showing job description.
     *
     * @param idJob ID of the job whose description is looked for
     * @return a String containing a detailed description of the job
     */
    @GetMapping("/details")
    public String showJobDescription(@RequestParam Long idJob) {
        return jobService.showJobDescription(idJob);
    }

    /**
     * Handles HTTP POST requst for creating a new job.
     *
     * @param jobDTO a Data Transfer Object used for creating the job
     * @return newly created Job
     */
    @PostMapping("")
    @Secured("ROLE_OWNER")
    public Job createJob(@RequestBody CreateJobDTO jobDTO) {
        return jobService.createJob(jobDTO.getJobName(), jobDTO.getJobDescription());
    }

}
