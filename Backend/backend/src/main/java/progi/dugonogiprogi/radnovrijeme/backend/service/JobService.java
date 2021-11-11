package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;

import java.util.List;

@Service
public interface JobService {

    List<Job> listAll();

    String showJobDescription(Long idJob);

    Job createJob(String jobName, String jobDescription);
}
