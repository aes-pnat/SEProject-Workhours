package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;

import java.util.Optional;

/**
 * Repository of jobs in the firm.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Override
    void deleteById(Integer id);
}
