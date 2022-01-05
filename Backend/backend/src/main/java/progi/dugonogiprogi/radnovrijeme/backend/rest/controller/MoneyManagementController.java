package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.MoneyManagementService;

/**
 * REST controller for the web application money management site.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/moneymanagement")
public class MoneyManagementController {

    /**
     * Injection of values of {@link MoneyManagementService} properties
     */
    @Autowired
    MoneyManagementService moneyManagementService;

    @Secured({"ROLE_OWNER"})
    @PostMapping("/expense")
    public ResponseEntity<?> seeExpenseDifference(@RequestParam double price) {
        return ResponseEntity.ok().body(moneyManagementService.seeExpense(price));

    }

    @Secured({"ROLE_OWNER"})
    @PostMapping("/profit")
    public ResponseEntity<?> seeProfitDifference(@RequestParam double price) {
        return ResponseEntity.ok().body(moneyManagementService.seeProfit(price));
    }
}
