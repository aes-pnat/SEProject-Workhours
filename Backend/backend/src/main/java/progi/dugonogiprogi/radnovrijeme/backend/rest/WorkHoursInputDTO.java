package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import java.util.List;

public class WorkHoursInputDTO {
   String name;
   String surname;
   List<WorkHoursInput> workHoursInput;
   Long hoursDoneSum;

   public WorkHoursInputDTO(Employee employee, List<WorkHoursInput> workHoursInput) {
       this.name = employee.getName();
       this.surname = employee.getSurname();
       this.workHoursInput = workHoursInput;
       this.hoursDoneSum = workHoursInput.stream().mapToLong(w -> Long.parseLong(w.getWorkHoursDone())).sum();
   }
}
