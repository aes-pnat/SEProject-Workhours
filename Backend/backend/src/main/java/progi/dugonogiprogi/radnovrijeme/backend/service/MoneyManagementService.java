package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MoneyManagementDTO;

@Service
public interface MoneyManagementService {

    MoneyManagementDTO seeExpense(double price);

    MoneyManagementDTO seeProfit(double price);
}
