package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingGroupException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.JobService;

import java.util.List;
import java.util.Optional;

/**
 * Provide some functionalities regarding jobs.
 */
@Slf4j
@Service
public class JobServiceJpa implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmployeegroupRepository employeegroupRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeetaskRepository employeetaskRepository;

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
        Optional<Job> job = jobRepository.findById(id);
        if(job.isEmpty()) {
            log.error("{}: Deleting job failed: Job with id {} does not exist", user, id);
            throw new EntityMissingException("Job with id " + id + " does not exist");
        }
        log.info("{}: Deleting job successful: Deleted job with id: {}", user, id);
        Optional<List<Group>> groups = groupRepository.findByIdjob(job.get());
        if (groups.isPresent()) {
            for (Group g : groups.get()) {
                for (Employeegroup eg : employeegroupRepository.findAll()) {
                    if (eg.getId().getIdgroup().equals(g.getId()))
                        employeegroupRepository.delete(eg);
                }
                groupRepository.delete(g);
            }
        }
        List<Task> tasks = taskRepository.findByIdjob_Id(id);
        for (Task t : tasks) {
            for (Employeetask et : employeetaskRepository.findAll()) {
                if (et.getId().getIdtask().equals(t.getId())) {
                    employeetaskRepository.delete(et);
                }
            }
            taskRepository.delete(t);
        }
        jobRepository.deleteById(id);
        return id;
    }
}
