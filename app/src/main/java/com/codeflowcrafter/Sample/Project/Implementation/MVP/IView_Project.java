package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;

/**
 * Created by aiko on 5/1/17.
 */


interface IView_Project_Events
{
    void OnOpenProjectEntryCompletion();
}

public interface IView_Project extends IView_Project_Events, IView<IProjectRequests> {
}
