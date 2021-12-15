package progi.dugonogiprogi.radnovrijeme.backend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeetaskRepository extends JpaRepository<Employeetask, EmployeetaskId> {

    Optional<List<Employeetask>> findById_Idemployee(String idemployee);

}