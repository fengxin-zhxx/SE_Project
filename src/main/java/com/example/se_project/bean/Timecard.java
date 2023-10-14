package com.example.se_project.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timecard {
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
