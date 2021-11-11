package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.Optional;

@Service
public interface GroupService {

    void createGroup(String name, Employee leader);

    void assignJob(Job job, Long idGroup);

    void assignTask(Task task, String idEmployee);

    void edit(Long idGroup, String idNewEmployee, String idOldEmployee);

    void delete(Long idGroup);

    boolean add(Long idGroup, Employee worker);

    boolean remove(Long idGroup, Employee worker);

    Group fetchGroup(long groupId);

    Employee fetchEmployee(String idEmployee);

    Optional<Group> findById(long groupId);

    Optional<Employee> findByEmployeeId(String idEmployee);
}