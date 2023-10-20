package com.example.se_project.mapper;

import com.example.se_project.bean.PayrollRecord;
import com.example.se_project.bean.Timecard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PayrollRecordMapper {
    @Insert(
            "INSERT INTO payroll_record(`payroll_date`, `payroll_amount`, `status`, `employee_id`, " +
                    "`start_date`, `end_date`, `pay_method`) " +
                    "values (#{payrollDate}, #{payrollAmount}, #{status}, #{employeeId}, #{startDate}, #{endDate}, #{payMethod})"
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
                    "`pay_method` = #{payMethod}\n" +
                    "WHERE `payroll_record_id` = #{payrollRecordId}"
    )
    void updatePayrollRecord(PayrollRecord payrollRecord);

    @Select("SELECT * FROM `payroll_record` WHERE `employee_id` = #{employeeId}")
    List<PayrollRecord> getPayrollRecordByEmployeeRecordId(Integer employeeId);

    @Select("SELECT * FROM `payroll_record` WHERE `payroll_record_id` = #{payrollRecordId}")
    PayrollRecord getPayrollRecordByPayrollRecordId(Integer payrollRecordId);

    @Update(
            "UPDATE `payroll_record`\n" +
                    "SET\n" +
                    "`status` = '已确认'\n" +
                    "WHERE `payroll_record_id` = #{payrollRecordId}"
    )
    void confirmPayrollRecord(Integer payrollRecordId);


    @Select("SELECT IFNULL(sum(payroll_amount), 0) totalPay, #{employeeId} employeeId , #{startDate} startDate, #{endDate} endDate\n" +
            "FROM `payroll_record`\n" +
            "WHERE employee_id = #{employeeId} AND start_date >= #{startDate} AND end_date <= #{endDate}")
    List<Map<String, Object>> getTotalPay(Integer employeeId, String startDate, String endDate);

    @Select("SELECT * FROM `payroll_record` ORDER BY `payroll_date` DESC")
    List<PayrollRecord> getAllPayrollRecord();

    @Select("SELECT * FROM `payroll_record` WHERE start_date = #{startDate} AND end_date = #{endDate} AND employee_id = #{employeeId}")
    List<PayrollRecord> getPayrollRecordByTimecard(Timecard timecard);
}