package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/workhoursinput")
public class WorkHoursController {

    @Autowired
    WorkHoursService workHoursService;

    @PostMapping("")
    public ResponseEntity<?> createNewWorkHoursInput(@RequestBody WorkHoursInputDTO workHoursInputDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/workhoursinput").toUriString());
        return ResponseEntity.created(uri).body(workHoursService.createNewWorkHoursInput(workHoursInputDTO.getTask(), workHoursInputDTO.getDate(), workHoursInputDTO.getHoursDone(), workHoursInputDTO.getIdEmployee()));
    }

    @GetMapping("")
    public ResponseEntity<?> listTaskNamesForEmployee(@RequestParam String idEmployee) {
        return ResponseEntity.ok().body(workHoursService.listTaskNamesForEmployee(idEmployee));
    }



}
