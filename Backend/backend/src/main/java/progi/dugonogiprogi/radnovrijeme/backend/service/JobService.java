package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;

@Service
public interface JobService {

    List<Job> listAll();

    String showJobDetails(Long idJob);

    Job createJob(String jobName, String jobDescription);
}
