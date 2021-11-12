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
public interface GroupRepository extends JpaRepository<Group,Long> {

    /**
     * Searches database to find requested group using given name.
     *
     * @param groupName String name of a group
     * @return Group if one with given name is found
     */

    Optional<Group> findByName(String groupName);

    /**
     * Searches database to find requested group using given identification number.
     *
     * @param idGroup Long value of an identification number of a group
     * @return Group if one with given id is found
     */

    Optional<Group> findById(Long idGroup);


}
