package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.impl.NoSuchGroupException;

import java.util.List;

@Service
public interface MyDataService {


    MyDataDTO myData(String username);
}
