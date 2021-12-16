package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MapService;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    MapService mapService;


    public List<LocationDataDTO> showLocationData() {
        return mapService.showLocationData();
    }






}
