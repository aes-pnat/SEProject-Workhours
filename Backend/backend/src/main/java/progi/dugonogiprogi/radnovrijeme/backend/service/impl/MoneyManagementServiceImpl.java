package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.rest.MoneyManagementDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MoneyManagementService;

@Service
public class MoneyManagementServiceImpl implements MoneyManagementService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public MoneyManagementDTO getFinancialStatus() {
        return new MoneyManagementDTO(taskRepository.findAll());
    }
}
