package com.example.se_project.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.se_project.bean.Employee;
import com.example.se_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.se_project.utils.Result;

import java.util.Map;

@RestController
@CrossOrigin
public class AdministratorController {
    @Autowired
    private EmployeeService employeeService;



    @RequestMapping("/AddEmployee")
    public Result AddEmployee(@RequestBody Map<String, Object> params){
        System.out.println("AddEmployee" + params);
        try{
            Integer employeeId = employeeService.insertEmployee(new Employee(params));
            return Result.ok("添加成功，员工ID为 " + employeeId);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/UpdateEmployee")
    public Result UpdateEmployee(@RequestBody Map<String, Object> params){
        System.out.println("UpdateEmployee" + params);
        try{
            employeeService.updateEmployee(new Employee(params));
            return Result.ok("修改成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/DeleteEmployee")
    public Result DeleteEmployee(@RequestBody Map<String, Object> params){
        System.out.println("DeleteEmployee" + params);
        try{
            employeeService.deleteEmployee((Integer) params.get("employee_id"));
            return Result.ok("删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}


