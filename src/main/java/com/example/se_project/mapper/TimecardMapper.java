package com.example.se_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.Timecard;
import com.example.se_project.bean.TimecardEntry;
import org.apache.ibatis.annotations.*;
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
    @Options(useGeneratedKeys=true, keyProperty="timecardEntryId", keyColumn="timecard_entry_id")
    void insertTimecardEntry(TimecardEntry timecardEntry);

    @Select("SELECT * FROM `timecard_entry` WHERE `timecard_id` = #{timecardId}")
    List<TimecardEntry> getTimecardEntriesByTimecardId(Integer timecardId);


    @Update("UPDATE `timecard`\n" +
            "SET\n" +
            "`submitted_date` = #{submitted_date},\n" +
            "`status` = 'Submitted'\n" +
            "WHERE `timecard_id` = #{timecard_id}\n")
    void submitTimecard(String submitted_date, Integer timecard_id);


    @Update("UPDATE `timecard_entry`\n" +
            "SET\n" +
            "`timecard_entry_id` = #{timecardEntryId},\n" +
            "`timecard_id` = #{timecardId},\n" +
            "`project_id` = #{projectId},\n" +
            "`work_date` = #{workDate},\n" +
            "`hours_worked` = #{hoursWorked}\n" +
            "WHERE `timecard_entry_id` = #{timecardEntryId};\n")
    void updateTimecardEntry(TimecardEntry timecardEntry);

    @Delete("DELETE FROM `timecard_entry` " +
            "WHERE `timecard_entry_id` = #{timecardEntryId}")
    void deleteTimecardEntry(Integer timecardEntryId);


    @Select("SELECT `timecard`.`timecard_id`,\n" +
            "    `timecard`.`employee_id`,\n" +
            "    `timecard`.`start_date`,\n" +
            "    `timecard`.`end_date`,\n" +
            "    `timecard`.`submitted_date`,\n" +
            "    `timecard`.`status`\n" +
            "FROM `se_project`.`timecard`\n" +
            "WHERE `employee_id` = #{employeeId}")
    List<Timecard> getTimecards(Integer employeeId);


    @Select("SELECT `timecard`.`timecard_id`,\n" +
            "    `timecard`.`employee_id`,\n" +
            "    `timecard`.`start_date`,\n" +
            "    `timecard`.`end_date`,\n" +
            "    `timecard`.`submitted_date`,\n" +
            "    `timecard`.`status`\n" +
            "FROM `se_project`.`timecard`\n" +
            "WHERE `timecard_id` = #{timecardId}")
    Timecard getTimecardByTimecardId(Integer timecardId);
}
