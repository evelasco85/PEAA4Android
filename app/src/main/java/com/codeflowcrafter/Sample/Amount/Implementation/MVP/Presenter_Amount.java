package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.Sample.InvocationDelegate;
import com.codeflowcrafter.Sample.SampleApplication;

/**
 * Created by aiko on 5/14/17.
 */

public class Presenter_Amount implements IRequests_Amount {
    private IView_Amount _view;
    private IStaticLogEntryWrapper _slc = SampleApplication.GetInstance().GetSLC();
    private IInvocationDelegates _invocationDelegate = new InvocationDelegate(_slc);

    public Presenter_Amount(IView_Amount view)
    {
        view.SetViewRequest(this);

        _view = view;

        _slc.SetComponent("Amount");
    }
}
