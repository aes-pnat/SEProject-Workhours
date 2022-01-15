package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeetask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeetaskId;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeetaskRepository extends JpaRepository<Employeetask, EmployeetaskId> {

    List<Employeetask> findById_Idemployee(String idemployee);

    Optional<List<Employeetask>> findById_Idtask(Integer idtask);

}