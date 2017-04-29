package com.codeflowcrafter.LogManagement;

/**
 * Created by aiko on 4/29/17.
 */

public class GlobalLogExtender implements ILogRetriever, ILogEmitter {

    private ILogEntry _outputLog;

    public ILogEntry GetOutputLog()
    {
        return _outputLog;
    }

    @Override
    public String RetrieveUser() {
        return "sa";
    }

    @Override
    public String RetrieveSessionId() {
        return "abc";
    }

    @Override
    public String RetrieveBusinessTransactionId() {
        return "123";
    }

    @Override
    public void OnLogEmit(ILogEntry log)
    {
        _outputLog = log;
    }
}
