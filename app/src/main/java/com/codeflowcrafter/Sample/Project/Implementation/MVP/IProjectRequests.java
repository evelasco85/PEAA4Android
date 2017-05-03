package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public interface IProjectRequests {
    void OpenAddProjectEntry();
    Project GetProjectById(int projectId);
    Project ConstructEmptyProject();
    void AddProject(Project project);
    void UpdateProject(Project project);

    void LoadAllProjects();
}
