package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.WorkHoursService;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping("/workhoursinput")
public class WorkHoursController {

    @Autowired
    WorkHoursService workHoursService;

    @PostMapping("")
    public ResponseEntity<?> createNewWorkHoursInput(@Validated @RequestBody WorkHoursInputDTO workHoursInputDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/workhoursinput").toUriString());
        return ResponseEntity.created(uri).body(workHoursService.createNewWorkHoursInput(workHoursInputDTO.getTask(), workHoursInputDTO.getDate(), workHoursInputDTO.getHoursDone(), workHoursInputDTO.getIdEmployee()));
    }

    @GetMapping("")
    public ResponseEntity<?> listTaskNamesForEmployee() {
        return ResponseEntity.ok().body(workHoursService.listTaskNamesForEmployee());
    }
}
