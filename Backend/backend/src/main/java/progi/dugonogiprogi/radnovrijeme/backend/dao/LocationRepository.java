package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
