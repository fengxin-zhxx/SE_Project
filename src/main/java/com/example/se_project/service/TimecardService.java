package com.example.se_project.service;

import com.example.se_project.bean.Timecard;
import com.example.se_project.bean.TimecardEntry;
import com.example.se_project.mapper.TimecardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TimecardService {
    @Autowired
    private TimecardMapper timecardMapper;


    public Timecard checkOrCreateTimecard(Integer employee_id){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date(System.currentTimeMillis()));
        List<Timecard>  currentTimecardList = timecardMapper.getCurrentTimecardByEmployeeId(employee_id, date);

        if(currentTimecardList.size() == 0){
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
            Timecard currentTimecard = currentTimecardList.get(0);
            List<TimecardEntry> currentTimecardEntries = timecardMapper.getTimecardEntriesByTimecardId(currentTimecard.getTimecardId());
            currentTimecard.setTimecardEntries(currentTimecardEntries);
            return currentTimecard;
        }


    }


    public Integer insertTimecard(TimecardEntry timecardEntry) {
        timecardMapper.insertTimecardEntry(timecardEntry);
        return timecardEntry.getTimecardEntryId();
    }

    public void submitTimecard(Integer timecardEntryId) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date(System.currentTimeMillis()));
        timecardMapper.submitTimecard(currentDate, timecardEntryId);
    }

    public List<TimecardEntry> getTimecardEntries(Timecard timecard, Integer page, Integer limit){
        List<TimecardEntry> timecardEntries = timecard.getTimecardEntries();
        if(page == null){
            return timecardEntries;
        }
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, timecardEntries.size());

        if (startIndex >= timecardEntries.size()) {
            // 如果请求的页码超出了数组的范围，返回空列表或适当的错误处理
            return new ArrayList<>();
        } else {
            // 根据页码和限制返回相应的数据
            return timecardEntries.subList(startIndex, endIndex);
        }
    }

    public void updateTimecardEntry(TimecardEntry timecardEntry) {
        timecardMapper.updateTimecardEntry(timecardEntry);
    }
}
