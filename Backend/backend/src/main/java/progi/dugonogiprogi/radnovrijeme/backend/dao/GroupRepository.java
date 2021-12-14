package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;

import java.util.Optional;

/**
 * Repository of groups in a firm.
 *
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}
