package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;

import java.util.List;

@Service
public interface MapService {

    List<LocationDataDTO> showLocationData();

}
