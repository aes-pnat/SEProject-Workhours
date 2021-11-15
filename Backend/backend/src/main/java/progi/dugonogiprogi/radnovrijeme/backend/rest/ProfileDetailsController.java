package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;

import java.util.Optional;

@Controller
@RequestMapping("/profiledetails")
public class ProfileDetailsController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    Optional<Employee> showThisEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return employeeService.getASingleEmployee((String)auth.getPrincipal());
    }






}
