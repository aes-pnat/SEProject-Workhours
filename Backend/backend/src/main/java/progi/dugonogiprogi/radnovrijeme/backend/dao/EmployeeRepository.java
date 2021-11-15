package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

/**
 * Repository for employees in a company.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    /**
     * Searches database to find requested employee using given pid.
     *
     * @param pid String value of employees personal identification number
     * @return Employee if one with given pid is found
     */
    Optional<Employee> findByPid(String pid);

    Employee findByUsername(String username);

}
