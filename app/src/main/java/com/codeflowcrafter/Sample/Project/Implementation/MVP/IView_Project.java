package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

/**
 * Created by aiko on 5/1/17.
 */


interface IView_Project_Events
{
    void OnOpenAddProjectEntryCompletion();
}

public interface IView_Project extends IView_Project_Events, IView<IProjectRequests> {
}
