package com.example.se_project.service;

import com.example.se_project.bean.Project;
import com.example.se_project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    public Project getProjectById(Integer projectId) {
        return projectMapper.getProjectById(projectId);
    }

    public List<Project> getAllProjects() {
        return projectMapper.getAllProjects();
    }


}
