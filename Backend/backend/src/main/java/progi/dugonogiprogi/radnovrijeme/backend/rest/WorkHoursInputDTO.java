package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import java.util.List;

public class WorkHoursInputDTO {
   private String name;
   private String surname;
   private List<WorkHoursInput> workHoursInput;
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
