package progi.dugonogiprogi.radnovrijeme.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.JobRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;


@SpringBootApplication
public class BackendApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(JobRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Job("djelatnost1", "opis1"));
			repository.save(new Job("djelatnost2", "opis2"));
			repository.save(new Job("djelatnost3", "opis3"));
			repository.save(new Job("djelatnost4", "opis4"));
			repository.save(new Job("djelatnost5", "opis5"));
		};
	}

	@Bean
	public CommandLineRunner demo2(EmployeeRepository repository, RoleRepository roleRepo) {
		return (args) -> {
			// save a few customers
			roleRepo.save(new Role("ROLE_LEADER"));
			repository.save(new Employee(
					"00000000000", "us1", "pass1", "email1", "name1", "lastname1", roleRepo.findByName("ROLE_LEADER").get())
			);
			repository.save(new Employee(
					"00000000001", "us2", "pass2", "email2", "name2", "lastname2", roleRepo.findByName("ROLE_LEADER").get())
			);
			repository.save(new Employee(
					"00000000002", "us3", "pass3", "email3", "name3", "lastname3", roleRepo.findByName("ROLE_LEADER").get())
			);
		};
	}


	@Bean
	public CommandLineRunner demo3(GroupRepository repository, EmployeeRepository empRepo, JobRepository jobRepo) {
		return (args) -> {
			// save a few customers
			repository.save(new Group("grupa1", empRepo.findByUsername("us1").get(), jobRepo.findByName("djelatnost1").get()));
			repository.save(new Group("grupa2", empRepo.findByUsername("us2").get(), jobRepo.findByName("djelatnost1").get()));
			repository.save(new Group("grupa3", empRepo.findByUsername("us3").get(), jobRepo.findByName("djelatnost1").get()));
		};
	}

}
