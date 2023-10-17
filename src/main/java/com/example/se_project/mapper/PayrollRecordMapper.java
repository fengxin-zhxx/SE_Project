package com.example.se_project.mapper;

import com.example.se_project.bean.PayrollRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRecordMapper {
    @Insert(
            "INSERT INTO payroll_record(`payroll_date`, `payroll_amount`, `status`, `employee_id`, " +
                    "`start_date`, `end_date`) " +
                    "values (#{payrollDate}, #{payrollAmount}, #{status}, #{employeeId}, #{startDate}, #{endDate})"
    )
    void insertPayrollRecord(PayrollRecord payrollRecord);

    @Update(
            "UPDATE `payroll_record`\n" +
                    "SET\n" +
                    "`payroll_date` = #{payrollDate},\n" +
                    "`payroll_amount` = #{payrollAmount},\n" +
                    "`status` = #{status},\n" +
                    "`employee_id` = #{employeeId},\n" +
                    "`start_date` = #{startDate},\n" +
                    "`end_date` = #{endDate}\n" +
                    "WHERE `payroll_record_id` = #{payrollRecordId}"
    )
    void updatePayrollRecord(PayrollRecord payrollRecord);

    @Select("SELECT * FROM `payroll_record` WHERE `employee_id` = #{employeeId}")
    List<PayrollRecord> getPayrollRecordByEmployeeRecordId(Integer employeeId);

    @Update(
            "UPDATE `payroll_record`\n" +
                    "SET\n" +
                    "`status` = '已确认'\n" +
                    "WHERE `payroll_record_id` = #{payrollRecordId}"
    )
    void confirmPayrollRecord(Integer payrollRecordId);
}