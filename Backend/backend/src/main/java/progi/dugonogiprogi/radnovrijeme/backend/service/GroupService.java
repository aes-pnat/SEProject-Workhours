package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnik;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnost;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Zadatak;

import java.util.Optional;

@Service
public interface GroupService {

    void createGroup(String name, Djelatnik leader);

    void assignJob(Djelatnost job, Long idGroup);

    void assignTask(Zadatak task, String idEmployee);

    void edit(Long idGroup, String idNewEmployee, String idOldEmployee);

    void delete(Long idGroup);

    boolean add(Long idGroup, Djelatnik worker);

    boolean remove(Long idGroup, Djelatnik worker);

    Grupa fetchGroup(long groupId);

    Djelatnik fetchEmployee(String idEmployee);

    Optional<Grupa> findById(long groupId);

    Optional<Djelatnik> findByEmployeeId(String idEmployee);
}