package com.example.se_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("SELECT * FROM `projects` WHERE `project_id` = #{projectId}")
    Project getProjectById(Integer projectId);

    @Select("SELECT * FROM `projects`")
    List<Project> getAllProjects();
}
