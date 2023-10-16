package com.example.se_project.service;

import com.example.se_project.bean.PayrollRecord;
import com.example.se_project.mapper.PayrollRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollRecordService {
    @Autowired
    private PayrollRecordMapper payrollRecordMapper;


    public List<PayrollRecord> getPayrollRecordByEmployeeRecordId(Integer payrollRecordId) {
        return payrollRecordMapper.getPayrollRecordByEmployeeRecordId(payrollRecordId);
    }


}
