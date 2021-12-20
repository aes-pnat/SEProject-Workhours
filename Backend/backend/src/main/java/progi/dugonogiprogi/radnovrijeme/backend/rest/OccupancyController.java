package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.EmployeeDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.OccupancyDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.OccupancyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin("*")
@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    @Autowired
    OccupancyService occupancyService;

    @GetMapping("")
    public ResponseEntity<?> listAllEmployees() {
        return ResponseEntity.ok().body(occupancyService.listAllEmployees());
    }

    @PostMapping("")
    public ResponseEntity<?> isOccupied(@RequestBody OccupancyDTO occupancyDTO) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return ResponseEntity.ok().body(occupancyService.isOccupied(occupancyDTO.getIdEmployee(), formatter.parse(occupancyDTO.getDateStart()), formatter.parse(occupancyDTO.getDateEnd())));
    }

}