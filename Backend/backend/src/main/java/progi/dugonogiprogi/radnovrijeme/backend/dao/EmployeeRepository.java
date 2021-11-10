package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.boot.autoconfigure.web.servlet.DefaultJerseyApplicationPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnik;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Djelatnik,Long> {

    Optional<Djelatnik> findByEmployeeId(String idEmployee);

}
