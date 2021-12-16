package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.EmployeeDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.OccupancyService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    @Autowired
    OccupancyService occupancyService;

    @GetMapping("")
    public List<EmployeeDTO> listAllEmployees() {
        return occupancyService.listAllEmployees();
    }

    @PostMapping("")
    public String isOccupied(@RequestParam String id, @RequestParam Date dateStart, @RequestParam Date dateEnd) {
        return occupancyService.isOccupied(id, dateStart, dateEnd);
    }

}