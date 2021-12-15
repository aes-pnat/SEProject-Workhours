package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MoneyManagementDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MoneyManagementService;

/**
 * REST controller for the web application money management site.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/moneymanagement")
public class MoneyManagementController {


}
