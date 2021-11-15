package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import org.springframework.scheduling.config.IntervalTask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class OccupancyDTO {

    private List<Interval> intervals;
    private String employeeId;


    public OccupancyDTO(List<Interval> intervals, String employeeId) {
        this.intervals = intervals;
        this.employeeId = employeeId;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public static class Interval {

        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public Interval(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

    }
}
