package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnost;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;

@Service
public interface UserService {

    Grupa createGroup(String name);

    Djelatnost defineWork(String work);
}