package com.example.se_project.service;

import com.example.se_project.bean.Employee;
import com.example.se_project.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
