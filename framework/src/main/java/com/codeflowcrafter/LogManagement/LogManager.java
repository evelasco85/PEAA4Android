package com.codeflowcrafter.LogManagement;

/**
 * Created by aiko on 4/29/17.
 */

interface ILogRetriever
{
    String RetrieveUserDelegate();
    String RetrieveSessionIdDelegate();
    String RetrieveBusinessTransactionIdDelegate();
}

interface ILogEmitter
{
    void OnLogEmit(ILogEntry log);
}

interface ILogManager
{
    void SetRetriever(ILogRetriever retriever);
    void SetEmitter(ILogEmitter emitter);
    ILogEntry CreateLogEntry(Priority priority);
    void EmitLog(ILogEntry log);
}

public class LogManager implements ILogManager {
    static ILogManager s_instance = new LogManager();

    ILogRetriever _retriever;
    ILogEmitter _emitter;

    private LogManager()
    {
    }

    public static ILogManager GetInstance()
    {
        return s_instance;
    }

    public void SetRetriever(ILogRetriever retriever)
    {
        if(_retriever != null) return;      //Set once only to avoid threading complications

        _retriever = retriever;
    }

    public void SetEmitter(ILogEmitter emitter)
    {
        if(_emitter != null) return;      //Set once only to avoid threading complications

        _emitter = emitter;
    }

    public ILogEntry CreateLogEntry(Priority priority)
    {
        return null;
    }

    public void EmitLog(ILogEntry log)
    {
        if(_emitter != null) _emitter.OnLogEmit(log);
    }
}
