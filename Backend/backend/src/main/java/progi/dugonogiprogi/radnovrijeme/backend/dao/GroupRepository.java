package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Grupa,Long> {

    Optional<Grupa> findByName(String groupName);

}
