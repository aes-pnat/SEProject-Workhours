package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnost;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;

@Service
public interface GroupService {

    Grupa createGroup(String name);

    Djelatnost defineWork(String workName, String workDescription);

    void registerUser(String name, String surname, String userName, String OIB, String email);
}