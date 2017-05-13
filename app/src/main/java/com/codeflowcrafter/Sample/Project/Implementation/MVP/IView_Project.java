package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

interface IView_Events
{
    //UI Prompting Events
    void OnPromptExecution_AddProjectEntry();
    void OnPromptExecution_EditProjectEntry(Project project);
    void OnPromptExecution_DeleteProjectEntry(Project project);
    void OnPromptExecution_ProjectDetail(Project project);
    void OnPromptExecution_AddAmountEntry(Project project);
    void OnPromptExecution_AmountList(Project project);

    void OnLoadProjectsViaLoaderCompletion(List<Project> projects);
}

public interface IView_Project extends IView_Events, IView<IRequests_Project> {
}
