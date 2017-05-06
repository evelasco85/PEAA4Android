package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

interface IView_Project_Events
{
    //UI Prompting Events
    void OnPromptCompletion_AddProjectEntry();
    void OnPromptCompletion_EditProjectEntry(Project project);
    void OnPromptCompletion_DeleteProjectEntry(Project project);
    void OnPromptCompletion_ProjectDetail(Project project);
    void OnPromptCompletion_AddAmountEntry(Project project);
    void OnPromptCompletion_AmountList(Project project);

    void OnLoadProjectsViaLoaderCompletion(List<Project> projects);
}

public interface IView_Project extends IView_Project_Events, IView<IProjectRequests> {
}
