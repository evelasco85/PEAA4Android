package com.codeflowcrafter.LogManagement;

import com.codeflowcrafter.LogManagement.Interfaces.ILogCreator;
import com.codeflowcrafter.LogManagement.Interfaces.ILogEntry;

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

interface ILogManager extends ILogCreator
{
    void SetRetriever(ILogRetriever retriever);
    void SetEmitter(ILogEmitter emitter);
}

public class LogManager implements ILogManager {
    private static ILogManager s_instance = new LogManager();

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

    public ILogEntry CreateLogEntry(Priority priority)
    {
        ILogEntry log = new LogEntry(
                TimeZone.getDefault(),
                Calendar.getInstance().getTime(),
                (_retriever == null) ? "N/A" : _retriever.RetrieveUser(),
                (_retriever == null) ? "N/A" : _retriever.RetrieveSessionId(),
                (_retriever == null) ? "N/A" : _retriever.RetrieveBusinessTransactionId(),
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
