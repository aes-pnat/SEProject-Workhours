package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.JobService;

import java.net.URI;

/**
 * Handles requests for Jobs page.
 */

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("")
    public ResponseEntity<?> listAllJobs() {
        return ResponseEntity.ok().body(jobService.listAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createJob(@Validated @RequestBody Job job) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/jobs/add").toUriString());
        return ResponseEntity.created(uri).body(jobService.createJob(job));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteJob(@RequestParam Integer id) {
        return ResponseEntity.ok().body(jobService.deleteJob(id));
    }
}
