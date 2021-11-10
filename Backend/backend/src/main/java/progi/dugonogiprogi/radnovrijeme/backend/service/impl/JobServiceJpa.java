package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepo;

    @Override
    public List<Job> listAll() {
        jobRepo.findAll();
    }

    @Override
    public String showJobDetails(Long idJob) {
        jobRepo.findById(idJob);
    }

    @Override
    public Job createJob(Job job) {
        return null;
    }
}
