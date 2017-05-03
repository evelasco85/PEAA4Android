package com.codeflowcrafter.Sample.Project.Implementation.MVP;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.PEAA.Domain.Interfaces.IDomainObject;
import com.codeflowcrafter.PEAA.Interfaces.IRepository;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.QueryAllProjects;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.QueryProjectById;
import com.codeflowcrafter.Sample.SampleApplication;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class Presenter_Project implements IProjectRequests, IInvocationDelegates {
    IView_Project _view;
    IStaticLogEntryWrapper _slc = SampleApplication.GetInstance().GetSLC();
    IRepository<Project> _repository = DataSynchronizationManager.GetInstance().GetRepository(Project.class);

    public Presenter_Project(IView_Project view)
    {
        view.SetViewRequest(this);

        _view = view;

        _slc.SetComponent("Project");
    }

    public void OpenAddProjectEntry()
    {
        _slc.SetEvent("Open Project Entry");
        _view.OnOpenAddProjectEntryCompletion();
        _slc.EmitLog(Priority.Info, Status.Success);
    }

    public void LoadAllProjects()
    {
        _view.OnLoadAllProjectsCompletion(_repository.Matching(new QueryAllProjects.Criteria()));
    }

    public void OpenEditProjectEntry(Project project) {
        _view.OnOpenEditProjectEntryCompletion(project);
    }

    public void AddProject(Project project)
    {
        IBaseMapper mapper = project.GetMapper();

        mapper.Insert(project, this);
    }

    public void UpdateProject(Project project)
    {
        IBaseMapper mapper = project.GetMapper();

        mapper.Update(project, this);
    }

    @Override
    public Hashtable GetResults() {
        return null;
    }

    @Override
    public void SetResults(Hashtable results) {

    }

    @Override
    public void SuccessfulInvocation(IDomainObject domainObject) {

    }

    @Override
    public void FailedInvocationDelegate(IDomainObject domainObject) {

    }
}
