package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.MoneyManagementDTO;

@Service
public interface MoneyManagementService {

    MoneyManagementDTO getFinancialStatus();
}
