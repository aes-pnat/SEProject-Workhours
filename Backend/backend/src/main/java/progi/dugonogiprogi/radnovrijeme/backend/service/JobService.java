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

    /**
     * Lists all jobs.
     *
     * @return a list of jobs
     */
    List<JobDTO> listAll();

    /**
     * Gets detailed description of a job.
     *
     * @param idJob ID of the job whose description is looked for
     * @return a String containing a detailed description of the job
     */
    String showJobDescription(Long idJob);

    /**
     * Creates a new job.
     *
     * @param jobName name of the new job
     * @param jobDescription description of the new job
     * @return newly created job
     */
    Job createJob(String jobName, String jobDescription);
}
