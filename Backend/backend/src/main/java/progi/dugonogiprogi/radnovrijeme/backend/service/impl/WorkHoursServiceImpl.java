package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.WorkHoursRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.util.List;

@Service
public class WorkHoursServiceImpl implements WorkHoursService {

    @Autowired
    WorkHoursRepository workHoursRepository;

    @Override
    public List<WorkHoursInput> listWorkHoursEmployee(String username) {
        return null;
    }
}
