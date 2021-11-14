package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepo;

    @Override
    public List<Job> listAll() {
        return jobRepo.findAll();
    }

    @Override
    public String showJobDescription(Long idJob) {return jobRepo.findById(idJob).getDescription();
    }

    @Override
    public Job createJob(String jobName, String jobDescription) {
        return jobRepo.save(new Job(jobName, jobDescription));
    }
}
