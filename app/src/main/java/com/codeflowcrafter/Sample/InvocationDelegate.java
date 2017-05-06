package com.codeflowcrafter.Sample;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.PEAA.Domain.Interfaces.IDomainObject;

import java.util.Hashtable;

/**
 * Created by aiko on 5/6/17.
 */

public class InvocationDelegate implements IInvocationDelegates {
    Hashtable _results;

    @Override
    public Hashtable GetResults() {
        return _results;
    }

    @Override
    public void SetResults(Hashtable results) {
        _results = results;
    }

    @Override
    public void SuccessfulInvocation(IDomainObject domainObject) {

    }

    @Override
    public void FailedInvocationDelegate(IDomainObject domainObject) {

    }
}
