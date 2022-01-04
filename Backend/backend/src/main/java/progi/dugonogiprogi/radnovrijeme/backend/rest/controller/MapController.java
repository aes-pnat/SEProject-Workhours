package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MapService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("")
    public List<LocationDataDTO> showLocationData() {
        return mapService.showLocationData();
    }






}
