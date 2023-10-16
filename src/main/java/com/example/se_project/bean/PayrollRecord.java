package com.example.se_project.bean;

import java.util.Date;

public class PayrollRecord {
    private Integer payrollRecordId;
    private String payrollDate;
    private Double payrollAmount;
    private String status;
    private Integer employeeId;
    private String startDate;
    private String endDate;

    public PayrollRecord(Integer payrollRecordId, String payrollDate, Double payrollAmount, String status, Integer employeeId, String startDate, String endDate) {
        this.payrollRecordId = payrollRecordId;
        this.payrollDate = payrollDate;
        this.payrollAmount = payrollAmount;
        this.status = status;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getPayrollRecordId() {
        return payrollRecordId;
    }

    public void setPayrollRecordId(Integer payrollRecordId) {
        this.payrollRecordId = payrollRecordId;
    }

    public String getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(String payrollDate) {
        this.payrollDate = payrollDate;
    }

    public Double getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(Double payrollAmount) {
        this.payrollAmount = payrollAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
