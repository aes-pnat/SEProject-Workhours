package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnost;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Zadatak;

@Service
public interface GroupService {

    Grupa createGroup(String name);

    Djelatnost assignJob(String workName, String workDescription);

    Zadatak assignTask(String taskName);

    Grupa edit(Grupa group);

    void delete(Grupa group);
}