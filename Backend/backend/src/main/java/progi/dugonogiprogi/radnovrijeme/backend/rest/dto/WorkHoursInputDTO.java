package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;

import java.time.LocalDate;
import java.util.List;

/**
 * Data transfer object that stores all work hours inputs and sum of all hours done for an employee.
 */
public class WorkHoursInputDTO {

    private String task;
    private LocalDate date;
    private Integer hoursDone;
    private String idEmployee;

    public WorkHoursInputDTO(String task, LocalDate date, Integer hoursDone, String idEmployee) {
        this.task = task;
        this.date = date;
        this.hoursDone = hoursDone;
        this.idEmployee = idEmployee;
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getHoursDone() {
        return hoursDone;
    }

    public String getIdEmployee() {
        return idEmployee;
    }
}
