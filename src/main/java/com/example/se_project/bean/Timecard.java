package com.example.se_project.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timecard {
    public class TimecardEntry {
        private Integer entryId;
        private Integer projectId;
        private String workDate;
        private Double hoursWorked;

        public TimecardEntry(Integer entryId, Integer projectId, String workDate, Double hoursWorked) {
            this.entryId = entryId;
            this.projectId = projectId;
            this.workDate = workDate;
            this.hoursWorked = hoursWorked;
        }

        public Integer getEntryId() {
            return entryId;
        }

        public void setEntryId(Integer entryId) {
            this.entryId = entryId;
        }

        public Integer getProjectId() {
            return projectId;
        }

        public void setProjectId(Integer projectId) {
            this.projectId = projectId;
        }

        public String getWorkDate() {
            return workDate;
        }

        public void setWorkDate(String workDate) {
            this.workDate = workDate;
        }

        public Double getHoursWorked() {
            return hoursWorked;
        }

        public void setHoursWorked(Double hoursWorked) {
            this.hoursWorked = hoursWorked;
        }
    }

    private Integer timecardId;
    private Integer employeeId;
    private String startDate;
    private String endDate;
    private List<TimecardEntry> timecardEntries;
    private String submittedDate;
    private String status;

    public Timecard(Integer timecardId, Integer employeeId, String startDate, String endDate, List<TimecardEntry> timecardEntries, String submittedDate, String status) {
        this.timecardId = timecardId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timecardEntries = timecardEntries;
        this.submittedDate = submittedDate;
        this.status = status;
    }
    public Timecard(Integer timecardId, Integer employeeId, Date startDate, Date endDate, String submittedDate, String status) {
        this.timecardId = timecardId;
        this.employeeId = employeeId;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        this.startDate = formatter.format(startDate);
        this.endDate = formatter.format(endDate);
        this.timecardEntries = new ArrayList<>();
        this.submittedDate = submittedDate;
        this.status = status;
    }
    public Timecard(Integer employeeId, String startDate, String endDate){
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timecardEntries = new ArrayList<>();
        this.submittedDate = null;
        this.status = "Draft";

    }

    public Integer getTimecardId() {
        return timecardId;
    }

    public void setTimecardId(Integer timecardId) {
        this.timecardId = timecardId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<TimecardEntry> getTimecardEntries() {
        return timecardEntries;
    }

    public void setTimecardEntries(List<TimecardEntry> timecardEntries) {
        this.timecardEntries = timecardEntries;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
