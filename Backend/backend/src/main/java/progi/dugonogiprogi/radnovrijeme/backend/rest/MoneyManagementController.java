package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.service.MoneyManagementService;


@RestController
@RequestMapping("/moneymanagement")
public class MoneyManagementController {
    
    @Autowired
    MoneyManagementService moneyManagementService;
    
    @GetMapping("")
    //Secured direktor
    public MoneyManagementDTO getFinancialStatus() {
        return moneyManagementService.getFinancialStatus();
    }

}
