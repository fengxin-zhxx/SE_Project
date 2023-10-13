package com.example.se_project.bean;


import java.util.Map;

public class Employee {
    public Integer employeeId;
    public String firstName;
    public String lastName;
    public String employeeType;
    public String mailAddress;
    public String socialSeurityNumber;
    public Double standardTaxDeductions;
    public Double otherDeductions;
    public String phoneNumber;
    public Double hourlyRate;
    public Double salary;
    public Double commissionRate;
    public Double hourLimit;
    public String payMethod;

    public Employee(Integer employeeId, String firstName, String lastName, String employeeType, String mailAddress, String socialSeurityNumber, Double standardTaxDeductions, Double otherDeductions, String phoneNumber, Double hourlyRate, Double salary, Double commissionRate, Double hourLimit, String payMethod) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeType = employeeType;
        this.mailAddress = mailAddress;
        this.socialSeurityNumber = socialSeurityNumber;
        this.standardTaxDeductions = standardTaxDeductions;
        this.otherDeductions = otherDeductions;
        this.phoneNumber = phoneNumber;
        this.hourlyRate = hourlyRate;
        this.salary = salary;
        this.commissionRate = commissionRate;
        this.hourLimit = hourLimit;
        this.payMethod = payMethod;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getSocialSeurityNumber() {
        return socialSeurityNumber;
    }

    public void setSocialSeurityNumber(String socialSeurityNumber) {
        this.socialSeurityNumber = socialSeurityNumber;
    }

    public Double getStandardTaxDeductions() {
        return standardTaxDeductions;
    }

    public void setStandardTaxDeductions(Double standartTaxDeductions) {
        this.standardTaxDeductions = standartTaxDeductions;
    }

    public Double getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(Double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Double getHourLimit() {
        return hourLimit;
    }

    public void setHourLimit(Double hourLimit) {
        this.hourLimit = hourLimit;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Employee(Map<String, Object> params){
        this.employeeId = (Integer)  params.get("employee_id");
        this.firstName = (String) params.get("first_name");
        this.lastName = (String) params.get("last_name");
        this.employeeType = (String) params.get("employee_type");
        this.mailAddress = (String) params.get("mail_address");
        this.socialSeurityNumber = (String) params.get("social_security_number");
        this.standardTaxDeductions = Double.valueOf(String.valueOf(params.get("standard_tax_deductions")));
        this.otherDeductions = Double.valueOf(String.valueOf(params.get("other_deductions")));
        this.phoneNumber = (String) params.get("phone_number");
        this.hourlyRate = Double.valueOf(String.valueOf(params.get("hourly_rate")));
        if(params.get("salary") != null) {
            this.salary = Double.valueOf(String.valueOf(params.get("salary")));
        }else{
            this.salary = null;
        }
        if(params.get("commission_rate") != null) {
            this.commissionRate = Double.valueOf(String.valueOf(params.get("commission_rate")));
        }else{
            this.commissionRate = null;
        }
        if(params.get("hour_limit") != null){
            this.hourLimit = Double.valueOf(String.valueOf(params.get("hour_limit")));
        }else{
            this.hourLimit = null;
        }
        this.payMethod = "pickup";
    }

}
