package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;
import com.codeflowcrafter.Sample.SampleApplication;

/**
 * Created by aiko on 5/1/17.
 */

public class Presenter_Project implements IProjectRequests {
    IView_Project _view;
    IStaticLogEntryWrapper _slc = SampleApplication.GetInstance().GetSLC();

    public Presenter_Project(IView_Project view)
    {
        view.SetViewRequest(this);

        _view = view;

        _slc.SetComponent("Project");
    }

    public void OpenProjectEntry()
    {
        _slc.SetEvent("Open Project Entry");
        _view.OnOpenProjectEntryCompletion();
        _slc.EmitLog(Priority.Info, Status.Success);
    }
}
