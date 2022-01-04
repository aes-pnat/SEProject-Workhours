package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MoneyManagementDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MoneyManagementService;

/**
 * REST controller for the web application money management site.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/moneymanagement")
public class MoneyManagementController {

    @Autowired
    MoneyManagementService moneyManagementService;

    @PostMapping("/expense")
    public ResponseEntity<?> seeExpenseDifference(@RequestParam double price) {
        return ResponseEntity.ok().body(moneyManagementService.seeExpense(price));

    }

    @PostMapping("/profit")
    public ResponseEntity<?> seeProfitDifference(@RequestParam double price) {
        return ResponseEntity.ok().body(moneyManagementService.seeProfit(price));
    }


}
