package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.RequestDeniedException;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.List;

/**
 * Provide some functionalities regarding jobs.
 */
@Service
public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepo;

    /**
     * Lists all jobs.
     *
     * @return a list of jobs
     */
    @Override
    public List<Job> listAll() {
        return jobRepo.findAll();
    }

    /**
     * Gets detailed description of a job.
     *
     * @param idJob ID of the job whose description is looked for
     * @return a String containing a detailed description of the job
     */
    @Override
    public String showJobDescription(Long idJob) {return jobRepo.findByIdJob(idJob).get().getDescription();
    }

    /**
     * Creates a new job.
     *
     * @param jobName name of the new job
     * @param jobDescription description of the new job
     * @return newly created job
     */
    @Override
    public Job createJob(String jobName, String jobDescription) {
        Assert.notNull(jobName, "Job name must be given!");
        Assert.notNull(jobDescription, "Job decription must be given!");
        if (jobRepo.countByName(jobName) > 0)
            throw new RequestDeniedException("A job with the name " + jobName + "already exists");
        return jobRepo.save(new Job(jobName, jobDescription));
    }
}
