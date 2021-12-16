package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeegroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchTaskException;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;

import java.util.*;

public class MyDataServiceJpa implements MyDataService {


    @Autowired
    EmployeetaskRepository employeeTaskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeegroupRepository employeegroupRepository;


    @Override
    public List<String> listGroupNamesForEmployee(String idEmployee) throws NoSuchGroupException {
       Optional<List<Employeegroup>> employeeGroupsList = employeegroupRepository.findById_Idemployee(idEmployee);
       if(!employeeGroupsList.isPresent()){
           throw new NoSuchGroupException("Employee with ID >" + idEmployee + "< does not belong to any group.");
       }
       List<Integer> list = new LinkedList<>();
       for (Employeegroup eg : employeeGroupsList.get())
            list.add(eg.getId().getIdgroup());
       List<String> groupNameList = new ArrayList<>();
       for(Group g : groupRepository.findAll())
           if(list.contains(g.getId()))
               groupNameList.add(g.getName());
       return groupNameList;
    }

    @Override
    public List<Task> listTasksForEmployee(String idEmployee) {
        Optional<List<Employeetask>> employeeTaskList = employeeTaskRepository.findById_Idemployee(idEmployee);
        if(!employeeTaskList.isPresent()) {
            throw new NoSuchTaskException("Employee with ID >" + idEmployee + "< doesn't have any tasks.");
        }
        List<Integer> taskIDList = new ArrayList<>();
        for (Employeetask et : employeeTaskList.get())
            taskIDList.add(et.getId().getIdtask());
        List<Task> taskList = new ArrayList<>();
        for(Task t : taskRepository.findAll())
            if(taskIDList.contains(t.getId()))
                taskList.add(t);

        return taskList;
    }

    @Override
    public Employee showDataForEmployee(String idEmployee) {
        Optional<Employee> employee = employeeRepository.findById(idEmployee);
        if(!employee.isPresent()) {
            throw new MissingEmployeeException("Employee with ID >" + "< doesn't exist.");
        }
        Employee employeeData = null;
        for(Employee e : employeeRepository.findAll()){
            if(e.getId().equals(idEmployee))
                employeeData = e;
        }
        return employeeData;
    }


}
