package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.JobService;

import java.util.List;

/**
 * Provide some functionalities regarding jobs.
 */
@Service
public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> listAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Integer deleteJob(Integer id) {
        jobRepository.deleteById(id);
        return id;
    }
}
