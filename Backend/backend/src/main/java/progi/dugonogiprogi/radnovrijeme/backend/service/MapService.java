package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;

import java.time.Instant;
import java.util.List;

@Service
public interface MapService {

    List<LocationDataDTO> showLocationData();

}
