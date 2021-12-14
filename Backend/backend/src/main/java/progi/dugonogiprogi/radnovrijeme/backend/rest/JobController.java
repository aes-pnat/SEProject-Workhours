package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.CreateJobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.JobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.JobService;

import java.util.List;

/**
 * Handles requests for Jobs page.
 */

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    @GetMapping("")



}
