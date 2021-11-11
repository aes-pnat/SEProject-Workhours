package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {


    Optional<Group> findByName(String groupName);
    Optional<Group> findById(String groupName);

}
