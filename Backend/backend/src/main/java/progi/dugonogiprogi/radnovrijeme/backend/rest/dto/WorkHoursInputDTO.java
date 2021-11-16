package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import java.util.List;

/**
 * Data transfer object that stores all work hours inputs and sum of all hours done for an employee.
 */
public class WorkHoursInputDTO {
    /**
     * Name of the employee.
     */
   private String name;
    /**
     * Surname of the employee.
     */
   private String surname;
   /**
    * All work hours inputs that employee has inputted.
    */
   private List<WorkHoursInput> workHoursInput;
    /**
     * Sum of all hours the employee has done.
     */
   private Long hoursDoneSum;

   public WorkHoursInputDTO(Employee employee, List<WorkHoursInput> workHoursInput) {
       this.name = employee.getName();
       this.surname = employee.getSurname();
       this.workHoursInput = workHoursInput;
       this.hoursDoneSum = workHoursInput.stream().mapToLong(w -> Long.parseLong(w.getWorkHoursDone())).sum();
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<WorkHoursInput> getWorkHoursInput() {
        return workHoursInput;
    }

    public void setWorkHoursInput(List<WorkHoursInput> workHoursInput) {
        this.workHoursInput = workHoursInput;
    }

    public Long getHoursDoneSum() {
        return hoursDoneSum;
    }

    public void setHoursDoneSum(Long hoursDoneSum) {
        this.hoursDoneSum = hoursDoneSum;
    }
}
