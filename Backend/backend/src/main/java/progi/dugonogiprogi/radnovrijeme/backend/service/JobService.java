package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.JobDTO;

import java.util.List;

/**
 * Specifies how functionalities regarding jobs should be used.
 */
@Service
public interface JobService {

    List<Job> listAll();

    Job createJob(Job job);

    void deleteJob(Integer id);
}
