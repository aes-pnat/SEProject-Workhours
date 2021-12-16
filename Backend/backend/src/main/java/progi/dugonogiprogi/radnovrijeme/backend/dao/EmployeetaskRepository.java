package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeetask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeetaskId;

import java.util.List;
import java.util.Optional;

public interface EmployeetaskRepository extends JpaRepository<Employeetask, EmployeetaskId> {

    Optional<List<Employeetask>> findById_Idemployee(String idemployee);

}