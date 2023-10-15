package com.example.se_project.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TimecardEntry {
    private Integer timecardEntryId;
    private Integer timecardId;
    private Integer projectId;
    private String workDate;
    private Double hoursWorked;

    public TimecardEntry(Integer timecardEntryId, Integer timecardId, Integer projectId, Date workDate, Double hoursWorked) {
        this.timecardId = timecardId;
        this.timecardEntryId = timecardEntryId;
        this.projectId = projectId;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        this.workDate = formatter.format(workDate);
        this.hoursWorked = hoursWorked;
    }

    public TimecardEntry(Integer timecardEntryId, Integer timecardId, Integer projectId, String workDate, Double hoursWorked) {
        this.timecardId = timecardId;
        this.timecardEntryId = timecardEntryId;
        this.projectId = projectId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
    }

    public Integer getTimecardId() {
        return timecardId;
    }

    public void setTimecardId(Integer timecardId) {
        this.timecardId = timecardId;
    }
    public Integer getTimecardEntryId() {
        return timecardEntryId;
    }

    public void setTimecardEntryId(Integer timecardEntryId) {
        this.timecardEntryId = timecardEntryId;
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

    public TimecardEntry(Map<String, Object> params) {
        this.timecardId = Integer.valueOf(String.valueOf(params.get("timecard_id")));
        this.projectId = Integer.valueOf(String.valueOf(params.get("project_id")));
        this.workDate = (String) params.get("work_date");
        this.hoursWorked = Double.valueOf(String.valueOf(params.get("hours_worked")));
    }
}