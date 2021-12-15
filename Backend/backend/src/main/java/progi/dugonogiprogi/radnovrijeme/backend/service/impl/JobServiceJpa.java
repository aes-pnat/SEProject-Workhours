package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.JobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.RequestDeniedException;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.ArrayList;
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
    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }
}
