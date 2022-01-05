package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.OccupancyDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.OccupancyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@CrossOrigin("*")
@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    @Autowired
    OccupancyService occupancyService;

    @Secured({"ROLE_OWNER"})
    @GetMapping("")
    public ResponseEntity<?> listAllEmployees() {
        return ResponseEntity.ok().body(occupancyService.listAllEmployees());
    }

    @Secured({"ROLE_OWNER"})
    @PostMapping("")
    public ResponseEntity<?> isOccupied(@Validated @RequestBody OccupancyDTO occupancyDTO) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return ResponseEntity.ok().body(occupancyService.isOccupied(occupancyDTO.getIdEmployee(), formatter.parse(occupancyDTO.getDateStart()), formatter.parse(occupancyDTO.getDateEnd())));
    }

}