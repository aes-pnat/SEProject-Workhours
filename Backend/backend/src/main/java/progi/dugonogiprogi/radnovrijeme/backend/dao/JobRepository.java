package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;

import java.util.Optional;

/**
 * Repository of jobs in the firm.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Finds a job using its ID.
     *
     * @param idJob ID of the searched job
     * @return appropriate job if there is one with the given ID
     */
    Optional<Job> findByIdJob(Long idJob);

    int countByName(String name);

}
