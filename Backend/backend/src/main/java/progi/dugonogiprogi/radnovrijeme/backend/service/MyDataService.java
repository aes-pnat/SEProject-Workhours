package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.impl.NoSuchGroupException;

import java.util.List;

@Service
public interface MyDataService {


    List<String> listGroupNamesForEmployee(String idEmployee) throws NoSuchGroupException;

    List<Task> listTasksForEmployee(String idEmployee);

    Employee showDataForEmployee(String idEmployee);
}