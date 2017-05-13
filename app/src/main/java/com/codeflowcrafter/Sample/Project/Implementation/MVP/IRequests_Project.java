package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import android.content.CursorLoader;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public interface IRequests_Project {
    //User prompting operations (See corresponding events)
    void Prompt_AddProjectEntry();
    void Prompt_EditProjectEntry(Project project);
    void Prompt_DeleteProjectEntry(Project project);
    void Prompt_ProjectDetail(Project project);
    void Prompt_AddAmountEntry(Project project);
    void Prompt_AmountList(Project project);

    //Data source interactions
    void AddProject(Project project);
    void UpdateProject(Project project);
    void DeleteProject(Project project);

    void LoadProjectsViaLoader(CursorLoader loader);
    void CancelProjectEntry();
}
