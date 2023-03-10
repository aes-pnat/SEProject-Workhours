package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data transfer object that stores all work hours inputs and sum of all hours done for an employee.
 */
public class WorkHoursInputDTO {

    @NotEmpty(message = "Task name should be defined.")
    private String task;
    @NotNull(message = "A date for hours input should be selected.")
    private LocalDate date;
    @NotNull(message = "A number of hours should be defined.")
    private Integer hoursDone;

    public WorkHoursInputDTO(String task, LocalDate date, Integer hoursDone, String idEmployee) {
        this.task = task;
        this.date = date;
        this.hoursDone = hoursDone;
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

}
