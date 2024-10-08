package com.example.se_project.service;

import com.example.se_project.bean.Employee;
import com.example.se_project.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Integer insertEmployee(Employee employee){
        employeeMapper.insertEmployee(employee);
        return employee.getEmployeeId();
    }

    public void updateEmployee(Employee employee){
        employeeMapper.updateEmployee(employee);
    }

    public void deleteEmployee(Integer employeeId){
        employeeMapper.deleteEmployee(employeeId);
    }

    public Employee getEmployee(Integer employeeId) {
        return employeeMapper.getEmployee(employeeId);
    }

    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }


    public Boolean checkEmployeeId(Integer employeeId) {
        return getEmployee(employeeId) != null;
    }
}
