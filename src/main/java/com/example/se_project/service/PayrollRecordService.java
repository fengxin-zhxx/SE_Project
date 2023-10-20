package com.example.se_project.service;

import com.example.se_project.bean.Employee;
import com.example.se_project.bean.PayrollRecord;
import com.example.se_project.bean.Timecard;
import com.example.se_project.mapper.EmployeeMapper;
import com.example.se_project.mapper.PayrollRecordMapper;
import com.example.se_project.mapper.PurchaseOrderMapper;
import com.example.se_project.mapper.TimecardMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PayrollRecordService {
    @Autowired
    private PayrollRecordMapper payrollRecordMapper;

    @Autowired
    private TimecardMapper timecardMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    public List<PayrollRecord> getPayrollRecordByEmployeeRecordId(Integer payrollRecordId) {
        return payrollRecordMapper.getPayrollRecordByEmployeeRecordId(payrollRecordId);
    }


    public void confirmPayrollRecord(Integer payrollRecordId) {
        payrollRecordMapper.confirmPayrollRecord(payrollRecordId);
    }

    public List<Map<String, Object>> getTotalPay(Integer employeeId, String startDate, String endDate) {
        return payrollRecordMapper.getTotalPay(employeeId, startDate, endDate);
    }

    public List<PayrollRecord> getAllPayrollRecord() {
        return payrollRecordMapper.getAllPayrollRecord();

    }

    public Boolean checkRecord(Timecard timecard){
        List<PayrollRecord> payrollRecords = payrollRecordMapper.getPayrollRecordByTimecard(timecard);
        return payrollRecords.size() == 0;
    }

    public void refreshPayrollRecord() throws Exception {
        LocalDate currentLocalDate = LocalDate.now();
        // 获取当前日期的星期几
        Boolean fridayFlag = currentLocalDate.getDayOfWeek() == DayOfWeek.FRIDAY;
        Boolean monthFlag = currentLocalDate.equals(currentLocalDate.with(TemporalAdjusters.lastDayOfMonth()));

        if (!fridayFlag && !monthFlag) {
            throw new Exception("今天不是周五或月底");
        }
        // 选择当前周期内没提交的时间卡
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = sdf.format(currentDate);

        List<Timecard> timecardList = timecardMapper.getCurrentPeriodTimecards();
        for(Timecard timecard : timecardList){
            if(!checkRecord(timecard)){
                continue;
            }

            Employee employee = employeeMapper.getEmployee(timecard.getEmployeeId());
            PayrollRecord payrollRecord = null;
            Boolean submitFlag = Boolean.FALSE;
            switch (employee.getEmployeeType()){
                case "salaried":
                    if(!monthFlag){
                        break;
                    }
                    submitFlag = Boolean.TRUE;
                    payrollRecord = new PayrollRecord(formattedDate, employee.getSalary(),
                            employee.getEmployeeId(), timecard.getStartDate(), timecard.getEndDate(), employee.getPayMethod());
                    payrollRecordMapper.insertPayrollRecord(payrollRecord);
                    break;
                case "commissioned":
                    if(!monthFlag){
                        break;
                    }
                    submitFlag = Boolean.TRUE;
                    Integer count = purchaseOrderMapper.selectPurchaseOrderCount(timecard.getStartDate(), timecard.getEndDate());
                    payrollRecord = new PayrollRecord(formattedDate, count * employee.getCommissionRate(),
                            employee.getEmployeeId(), timecard.getStartDate(), timecard.getEndDate(), employee.getPayMethod());
                    payrollRecordMapper.insertPayrollRecord(payrollRecord);
                    break;
                case "hour":
                    if(!fridayFlag){
                        break;
                    }
                    submitFlag = Boolean.TRUE;
                    Integer totalHours = timecardMapper.getTotalHoursWorkedInATimecard(timecard.getTimecardId());
                    payrollRecord = new PayrollRecord(formattedDate, totalHours * employee.getHourlyRate(),
                            employee.getEmployeeId(), timecard.getStartDate(), timecard.getEndDate(), employee.getPayMethod());
                    payrollRecordMapper.insertPayrollRecord(payrollRecord);
                    break;

                default:
                    break;
            }
            if(submitFlag){
                timecardMapper.submitTimecard(formattedDate, timecard.getTimecardId());
            }
        }
    }
}
