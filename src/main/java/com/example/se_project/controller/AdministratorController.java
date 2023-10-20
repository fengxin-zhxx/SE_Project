package com.example.se_project.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.se_project.bean.Employee;
import com.example.se_project.service.EmployeeService;
import com.example.se_project.service.PayrollRecordService;
import com.example.se_project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.se_project.utils.Result;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AdministratorController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PayrollRecordService payrollRecordService;

    @RequestMapping("/GetAllEmployee")
    public Result GetAllEmployee(@RequestBody Map<String, Object> params){
        System.out.println("GetAllEmployee" + params);
        try{
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<Employee> employees = employeeService.getAllEmployee();
            Integer count = employees.size();
            return Result.ok().data("data", Utils.pageHelper(employees, page, limit)).data("count", count);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetEmployee")
    public Result GetEmployee(@RequestBody Map<String, Object> params){
        System.out.println("GetEmployee" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            Boolean checkRes = employeeService.checkEmployeeId(employeeId);
            if(!checkRes){
                return Result.error("员工ID不存在！");
            }
            Employee employee = employeeService.getEmployee(employeeId);
            return Result.ok().data("data", employee);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

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
            employeeService.deleteEmployee(Integer.valueOf(String.valueOf(params.get("employeeId"))));
            return Result.ok("删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/RefreshPayrollRecord")
    public Result RefreshPayrollRecord(){
        System.out.println("RefreshPayrollRecord");
        try{
            payrollRecordService.refreshPayrollRecord();
            return Result.ok("更新成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}


