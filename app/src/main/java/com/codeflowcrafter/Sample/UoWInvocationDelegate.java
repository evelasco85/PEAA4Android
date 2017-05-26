package com.codeflowcrafter.Sample;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;
import com.codeflowcrafter.PEAA.Domain.Interfaces.IDomainObject;
import com.codeflowcrafter.PEAA.Interfaces.UnitOfWorkAction;
import com.codeflowcrafter.PEAA.Interfaces.IUoWInvocationDelegates;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by aiko on 5/27/17.
 */

public class UoWInvocationDelegate implements IUoWInvocationDelegates {
    private Hashtable _results;

    public UoWInvocationDelegate()
    {
    }

    @Override
    public Hashtable GetResults() {
        return _results;
    }

    @Override
    public void SetResults(Hashtable results) {
        _results = results;
    }

    @Override
    public void SuccessfulUoWInvocationDelegate(IDomainObject domainObject, UnitOfWorkAction action) {
    }

    @Override
    public void FailedUoWInvocationDelegate(IDomainObject domainObject, UnitOfWorkAction action) {
    }
}
