package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.JobService;

import java.util.List;

/**
 * Provide some functionalities regarding jobs.
 */
@Slf4j
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
        String user = BackendApplication.getUser();
        log.info("{}: Creating job successful: Created job with id {}", user, job.getId());
        return jobRepository.save(job);
    }

    @Override
    public Integer deleteJob(Integer id) {
        String user = BackendApplication.getUser();
        log.info("{}: Deleting job successful: Deleted job with id: {}", user, id);
        jobRepository.deleteById(id);
        return id;
    }
}
