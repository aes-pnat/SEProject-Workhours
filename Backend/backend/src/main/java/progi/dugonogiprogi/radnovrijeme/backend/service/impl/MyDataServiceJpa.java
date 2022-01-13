package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupTaskDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingGroupException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchTaskException;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides some functionalities regarding information about employees
 *
 */

@Service
public class MyDataServiceJpa implements MyDataService {

    /**
     * Injection of values of {@link EmployeetaskRepository} properties
     */

    @Autowired
    EmployeetaskRepository employeetaskRepository;

    /**
     * Injection of values of {@link EmployeeRepository} properties
     */

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * Injection of values of {@link GroupRepository} properties
     */

    @Autowired
    GroupRepository groupRepository;

    /**
     * Injection of values of {@link TaskRepository} properties
     */
    @Autowired
    TaskRepository taskRepository;

    /**
     * Injection of values of {@link EmployeegroupRepository} properties
     */
    @Autowired
    EmployeegroupRepository employeegroupRepository;
    /**
     * Injection of values of {@link RoleRepository} properties
     */
    @Autowired
    RoleRepository roleRepository;




    @Override
    public MyDataDTO myData(String username) {
        MyDataDTO myData = new MyDataDTO();
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if(!employee.isPresent()){
            throw new MissingEmployeeException("Employee with username " +username+ " does not exist");
        }
        Employee e = employee.get();
        myData.setUsername(e.getUsername());
        myData.setName(e.getName());
        myData.setSurname(e.getSurname());
        myData.setPid(e.getId());
        myData.setEmail(e.getEmail());
        myData.setRoleName(e.getIdrole().getName());
        Optional<List<Employeegroup>> employeegroupList = employeegroupRepository.findById_Idemployee(myData.getPid());
        if(!employeegroupList.isPresent()) {
            throw new MissingGroupException("Employee with id "+myData.getPid()+ "doesn't have any groups");
        }

        List<Employeegroup> list = employeegroupList.get();
        List<String> lista = new LinkedList<>();
        for(Employeegroup eg : list){
            int groupId = eg.getId().getIdgroup();
            Optional<Group> group = groupRepository.findById(groupId);
            if(!group.isPresent()){
                throw new MissingGroupException("Group with id "+groupId+"does not exist");
            }
            Group g = group.get();
            lista.add(g.getName());
        }
        Optional<List<Group>> g = groupRepository.findByIdleader(e);
        if(g.isPresent()){
            lista.addAll(g.get().stream().map(Group::getName).collect(Collectors.toList()));
        }
        lista = lista.stream().distinct().toList();
        myData.setGroupNames(lista);
        Optional<List<Employeetask>> employeetaskList = employeetaskRepository.findById_Idemployee(myData.getPid());
        if(!employeetaskList.isPresent()) {
            throw new NoSuchTaskException("Employee with id "+myData.getPid()+"doesn't have any tasks");
        }
        List<Employeetask> employeetasks = employeetaskList.get();
        List<GroupTaskDTO> listaTaskova = new LinkedList<>();
        for(Employeetask et : employeetasks){
            int taskId = et.getId().getIdtask();
            Optional<Task> task = taskRepository.findById(taskId);
            if(!task.isPresent()){
                throw new NoSuchTaskException("Task with id "+taskId+"does not exist");
            }
            Task t = task.get();
            GroupTaskDTO dto = new GroupTaskDTO();
            dto.setTask(t);
            Optional<List<Employeegroup>> employeegroup = employeegroupRepository.findById_Idemployee(myData.getPid());
            if(employeegroup.isPresent()){
                for(Employeegroup eg : employeegroup.get()) {
                    Optional<Group> grupa = groupRepository.findById(eg.getId().getIdgroup());
                    if(grupa.isPresent()){
                        if(grupa.get().getIdjob().equals(t.getIdjob())){
                            dto.setGroup(grupa.get());
                        }
                    }
                }
            }
            //System.out.println(myData.getPid());
            for(Group grupa : groupRepository.findAll()){
                if(grupa.getIdleader().getId().equals(myData.getPid())
                    && grupa.getIdjob().equals(dto.getTask().getIdjob())){
                    dto.setGroup(grupa);
                }
            }
            listaTaskova.add(dto);


        }
        myData.setTasks(listaTaskova);
        return myData;

    }
}
