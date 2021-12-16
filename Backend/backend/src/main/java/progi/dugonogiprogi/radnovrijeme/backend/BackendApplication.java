package progi.dugonogiprogi.radnovrijeme.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

/*	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} */

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
/*
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
	public CommandLineRunner demo2(EmployeeRepository repository, RoleRepository roleRepo, EmployeeRepository empRepo, JobRepository jobRepo, GroupRepository grpRepo) {
		return (args) -> {
			// save a few customers
			roleRepo.save(new Role("ROLE_LEADER"));
			roleRepo.save(new Role("ROLE_EMPLOYEE"));
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
	public CommandLineRunner demo3(GroupRepository repository, EmployeeRepository empRepo, JobRepository jobRepo, GroupRepository grpRepo, RoleRepository roleRepo) {
		return (args) -> {
			// save a few customers
			repository.save(new Group("grupa1", empRepo.findByUsername("us1").get(), jobRepo.findByName("djelatnost1").get()));
			repository.save(new Group("grupa2", empRepo.findByUsername("us2").get(), jobRepo.findByName("djelatnost1").get()));
			repository.save(new Group("grupa3", empRepo.findByUsername("us3").get(), jobRepo.findByName("djelatnost1").get()));
			Employee emp = new Employee("00000000004", "us4", "pass4", "email4", "name4", "lastname4", roleRepo.findByName("ROLE_EMPLOYEE").get());
			empRepo.save(emp);
			Group grp = new Group("grupa4", empRepo.findByUsername("us1").get(), jobRepo.findByName("djelatnost4").get());
			Set<Employee> set = new HashSet<>();
			set.add(empRepo.findByUsername("us1").get());
			set.add(empRepo.findByUsername("us4").get());
			grp.setMembers(set);
			grpRepo.save(grp);
		};
	}
*/
}
