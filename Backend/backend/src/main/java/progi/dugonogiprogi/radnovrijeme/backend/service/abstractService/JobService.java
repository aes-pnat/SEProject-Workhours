package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;

import java.util.List;

/**
 * Specifies how functionalities regarding jobs should be used.
 */
@Service
public interface JobService {

    List<Job> listAll();

    Job createJob(Job job);

    Integer deleteJob(Integer id);
}
