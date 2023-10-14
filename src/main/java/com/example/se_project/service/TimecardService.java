package com.example.se_project.service;

import com.example.se_project.bean.Timecard;
import com.example.se_project.mapper.TimecardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TimecardService {
    @Autowired
    private TimecardMapper timecardMapper;


    public Timecard checkOrCreateTimecard(Integer employee_id){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date(System.currentTimeMillis()));
        List<Timecard>  currentTimecard = timecardMapper.getCurrentTimecardByEmployeeId(employee_id, date);

        if(currentTimecard.size() == 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String Monday = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String Sunday = sdf.format(calendar.getTime());
            Timecard timecard = new Timecard(employee_id, Monday, Sunday);
            timecardMapper.insertTimecard(timecard);
            return timecard;
        }else{
            return currentTimecard.get(0);
        }


    }


}
