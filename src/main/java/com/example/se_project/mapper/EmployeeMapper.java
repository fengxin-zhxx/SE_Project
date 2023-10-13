package com.example.se_project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Insert(
            "INSERT INTO employee(`first_name`, `last_name`, `employee_type`, " +
            "`mail_address`, `social_security_number`, `standart_tax_deductions`, " +
            "`other_deductions`, `phone_number`, `hourly_rate`, `salary`, `commission_rate`, " +
            "`hour_limit`, `pay_method`) values (" +
            "#{firstName}, #{lastName}, #{employeeType}, #{mailAddress}, #{socialSeurityNumber}, " +
            "#{standardTaxDeductions}, #{otherDeductions}, #{phoneNumber}, " +
            "#{hourlyRate}, #{salary}, #{commissionRate}, #{hourLimit}, #{payMethod})"
    )
    void insertEmployee(Employee employee);


    @Update(
            "UPDATE `employee`\n" +
                "SET\n" +
                "`first_name` = #{firstName},\n" +
                "`last_name` = #{lastName},\n" +
                "`employee_type` = #{employeeType},\n" +
                "`mail_address` = #{mailAddress},\n" +
                "`social_security_number` = #{socialSeurityNumber},\n" +
                "`standart_tax_deductions` = #{standardTaxDeductions},\n" +
                "`other_deductions` = #{otherDeductions},\n" +
                "`phone_number` = #{phoneNumber},\n" +
                "`hourly_rate` = #{hourlyRate},\n" +
                "`salary` = #{salary},\n" +
                "`commission_rate` = #{commissionRate},\n" +
                "`hour_limit` = #{hourLimit},\n" +
                "`pay_method` = #{payMethod}\n" +
                "WHERE `employee_id` = #{employeeId}"
    )
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM `employee`\n" +
            "WHERE `employee_id` = #{employeeId}")
    void deleteEmployee(Integer employeeId);

}
