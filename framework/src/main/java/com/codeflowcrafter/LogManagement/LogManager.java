package com.codeflowcrafter.LogManagement;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by aiko on 4/29/17.
 */

interface ILogRetriever
{
    String RetrieveUser();
    String RetrieveSessionId();
    String RetrieveBusinessTransactionId();
}

interface ILogEmitter
{
    void OnLogEmit(ILogEntry log);
}

interface ILogManager
{
    void SetRetriever(ILogRetriever retriever);
    void SetEmitter(ILogEmitter emitter);
    ILogEntry CreateLogEntry(Priority priority) throws NoSuchFieldException;
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
        if(_retriever != null) throw new IllegalArgumentException("retriever already setup");      //Set once only to avoid threading complications

        _retriever = retriever;
    }

    public void SetEmitter(ILogEmitter emitter)
    {
        if(_emitter != null)  throw new IllegalArgumentException("emitter already setup");      //Set once only to avoid threading complications

        _emitter = emitter;
    }

    public ILogEntry CreateLogEntry(Priority priority) throws NoSuchFieldException
    {
        if(_retriever == null) throw new NoSuchFieldException("Retriever has not been provided");

        ILogEntry log = new LogEntry(
                TimeZone.getDefault(),
                Calendar.getInstance().getTime(),
                _retriever.RetrieveUser(),
                _retriever.RetrieveSessionId(),
                _retriever.RetrieveBusinessTransactionId(),
                priority
        );

        log.SetStatus(Status.None);

        return log;
    }

    public void EmitLog(ILogEntry log)
    {
        if(_emitter != null) _emitter.OnLogEmit(log);
    }
}
