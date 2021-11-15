package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.service.MoneyManagementService;

/**
 * REST controller for the web application money management site.
 */
@RestController
@RequestMapping("/moneymanagement")
public class MoneyManagementController {
    
    @Autowired
    MoneyManagementService moneyManagementService;

    /**
     * This method returns financial data when the site is loading.
     * Only director has rights to access this method.
     * @return Data transfer object with profit and costs data.
     */
    @GetMapping("")
    @Secured("ROLE_OWNER")
    public MoneyManagementDTO getFinancialStatus() {
        return moneyManagementService.getFinancialStatus();
    }

}
