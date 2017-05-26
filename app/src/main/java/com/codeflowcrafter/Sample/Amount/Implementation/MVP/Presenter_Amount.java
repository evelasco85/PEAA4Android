package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.PEAA.Interfaces.IDataSynchronizationManager;
import com.codeflowcrafter.PEAA.Interfaces.IRepository;
import com.codeflowcrafter.PEAA.Interfaces.IUnitOfWork;
import com.codeflowcrafter.PEAA.Interfaces.IUoWInvocationDelegates;
import com.codeflowcrafter.PEAA.UnitOfWork;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.QueryAmountByProjectId;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.ToAmountTranslator;
import com.codeflowcrafter.Sample.MapperInvocationDelegate;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.SampleApplication;
import com.codeflowcrafter.Sample.UoWInvocationDelegate;

import java.util.List;

/**
 * Created by aiko on 5/14/17.
 */

public class Presenter_Amount implements IRequests_Amount {
    private IView_Amount _view;
    private IStaticLogEntryWrapper _slc = SampleApplication.GetInstance().CreateSLC();
    private ToAmountTranslator _translator = new ToAmountTranslator();
    private IInvocationDelegates _mapperInvocationDelegate = new MapperInvocationDelegate(_slc);
    private IUoWInvocationDelegates _uowInvocationDelegate = new UoWInvocationDelegate();

    public Presenter_Amount(IView_Amount view)
    {
        view.SetViewRequest(this);

        _view = view;

        _slc.SetComponent("Amount");
    }

    public void Prompt_AddAmountEntry()
    {
        _view.OnPromptExecution_AddAmountEntry();
        _slc.SetEvent("Open Amount Entry").EmitLog(Priority.Info, Status.Success);
    }

    @Override
    public void Prompt_EditAmountEntry(Amount amount) {
        _view.OnPromptExecution_EditAmountEntry(amount);
        _slc.SetEvent("Open amount editing").EmitLog(Priority.Info, Status.Success);

    }

    @Override
    public void Prompt_DeleteAmountEntry(Amount amount) {
        _view.OnPromptExecution_DeleteAmountEntry(amount);
    }

    @Override
    public void Prompt_AmountDetail(Amount amount) {
        _view.OnPromptExecution_AmountDetail(amount);
        _slc
                .SetEvent(String.format("Showing details of amount id %s", amount.GetId()))
                .EmitLog(Priority.Info, Status.Success);
    }

    @Override
    public void AddAmount(Project project, Amount amount) {
        IUnitOfWork uow = new UnitOfWork();

        UpdateProjectTotal(project, amount, false);
        uow.RegisterNew(amount, _mapperInvocationDelegate);
        uow.RegisterDirty(project, _mapperInvocationDelegate);
        uow.Commit(_uowInvocationDelegate);

        _slc.SetEvent("Amount Added").EmitLog(Priority.Info, Status.Success);
    }

    @Override
    public void UpdateAmount(Project project, Amount amount) {
        IUnitOfWork uow = new UnitOfWork();

        UpdateProjectTotal(project, amount, false);
        uow.RegisterDirty(amount, _mapperInvocationDelegate);
        uow.RegisterDirty(project, _mapperInvocationDelegate);
        uow.Commit(_uowInvocationDelegate);

        _slc
                .SetEvent(String.format("Updated amount id %s", amount.GetId()))
                .EmitLog(Priority.Info, Status.Success);
    }

    @Override
    public void DeleteAmount(Project project, Amount amount) {
        IUnitOfWork uow = new UnitOfWork();

        UpdateProjectTotal(project, amount, true);
        uow.RegisterRemoved(amount, _mapperInvocationDelegate);
        uow.RegisterDirty(project, _mapperInvocationDelegate);
        uow.Commit(_uowInvocationDelegate);
        _slc
                .SetEvent(String.format("Deleted amount id %s", amount.GetId()))
                .EmitLog(Priority.Info, Status.Success);
    }

    public void CancelAmountEntry()
    {
        _slc
                .SetEvent("Cancel amount entry window")
                .EmitLog(Priority.Info, Status.Success);
    }

    public void LoadAmountsViaLoader(int projectId)
    {
        IDataSynchronizationManager manager= DataSynchronizationManager.GetInstance();
        IRepository<Amount> repository = manager.GetRepository(Amount.class);
        QueryAmountByProjectId.Criteria criteria = new QueryAmountByProjectId.Criteria(projectId);
        List<Amount> entityList = repository.Matching(criteria);


        _view.OnLoadAmountsViaLoaderCompletion(entityList);
        _slc
                .SetEvent(String.format("Loaded amount count %d", entityList.size()))
                .EmitLog(Priority.Info, Status.Success);
    }

    private void UpdateProjectTotal(Project project, Amount amount, boolean reversal) {
        double currentTotal = project.GetTotal();

        if (!reversal) {
            if (amount.GetIsExpense())
                currentTotal -= amount.GetAmount();
            else
                currentTotal += amount.GetAmount();
        }
        else
        {
            if (amount.GetIsExpense())
                currentTotal += amount.GetAmount();
            else
                currentTotal -= amount.GetAmount();
        }

        project.SetTotal(currentTotal);
    }
}
