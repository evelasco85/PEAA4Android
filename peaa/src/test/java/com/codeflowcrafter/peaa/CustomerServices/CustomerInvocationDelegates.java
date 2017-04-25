package com.codeflowcrafter.peaa.CustomerServices;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.InvocationDelegates;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;

import java.util.Hashtable;

/**
 * Created by aiko on 4/8/17.
 */

public class CustomerInvocationDelegates implements InvocationDelegates {
    Hashtable _results;

    public Hashtable GetResults() {
        return _results;
    }

    public void SetResults(Hashtable results) {
        _results = results;
    }

    public void SuccessfulInvocation(IDomainObject domainObject) {

    }

    public void FailedInvocationDelegate(IDomainObject domainObject) {

    }
}
