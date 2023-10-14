package com.example.se_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.Timecard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface TimecardMapper extends BaseMapper<Timecard> {
    @Insert(
            "INSERT INTO timecard(`employee_id`, `start_date`, `end_date`, `status`) " +
                    "values (#{employeeId}, #{startDate}, #{endDate}, #{status})"
    )
    @Options(useGeneratedKeys=true, keyProperty="timecardId", keyColumn="timecard_id")
    void insertTimecard(Timecard timecard);

    @Update(
            "UPDATE `timecard`\n" +
                    "SET\n" +
                    "`submitted_date` = #{submittedDate},\n" +
                    "`status` = #{status}\n" +
                    "WHERE `timecard_id` = #{timecardId}"
    )
    void updateTimecardStatus(Timecard timecard);

    @Select("SELECT * FROM `timecard` WHERE `employee_id` = #{employeeId} " +
            "AND `start_date` <= #{currentDate} AND `end_date` >= #{currentDate}")
    List<Timecard> getCurrentTimecardByEmployeeId(Integer employeeId, String currentDate);

    @Insert(
            "INSERT INTO timecard_entry(`timecard_id`, `project_id`, `work_date`, `hours_worked`) " +
                    "values (#{timecardId}, #{projectId}, #{workDate}, #{hoursWorked})"
    )
    void insertTimecardEntry(Timecard.TimecardEntry timecardEntry);

    @Select("SELECT * FROM `timecard_entry` WHERE `timecard_id` = #{timecardId}")
    List<Map<String, Object>> getTimecardEntriesByTimecardId(Integer timecardId);
}
