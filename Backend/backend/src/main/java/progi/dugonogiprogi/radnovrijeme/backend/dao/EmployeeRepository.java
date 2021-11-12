package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

/**
 * Repository of employees in a firm
 *
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    /**
     * Searches database to find requested employee using given identification number
     *
     * @param idEmployee Long value of an identification number of an employee
     * @return Employee if one with given id is found
     */

    Optional<Employee> findByEmployeeId(String idEmployee);

}
