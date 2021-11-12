package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.List;

@Service
public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepo;

    @Override
    public List<Job> listAll() {
        return jobRepo.findAll();
    }

    @Override
    public String showJobDescription(Long idJob) {return jobRepo.findByIdJob(idJob).get().getDescription();
    }

    @Override
    public Job createJob(String jobName, String jobDescription) {
        return jobRepo.save(new Job(jobName, jobDescription));
    }
}
