package com.codeflowcrafter.Sample.Project.Implementation;

/**
 * Created by aiko on 5/1/17.
 */

public class Presenter_Project implements IProjectRequests {
    IView_Project _view;

    public Presenter_Project(IView_Project view)
    {
        view.SetViewRequest(this);

        _view = view;

        _view.AssociateViewToLocalVar();
        _view.SetViewHandlers();
    }

    public void OpenProjectEntry()
    {
        _view.OnOpenProjectEntryCompletion();
    }
}
