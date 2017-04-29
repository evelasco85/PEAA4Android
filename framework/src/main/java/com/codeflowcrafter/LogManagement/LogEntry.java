package com.codeflowcrafter.LogManagement;

import java.util.HashMap;
import java.util.TimeZone;
import java.util.Date;

/**
 * Created by aiko on 4/29/17.
 */

interface ILogEntry {
    TimeZone GetTimeZone();

    Date GetOccurence();

    String GetUser();

    String GetSessionId();

    String GetTransactionId();

    String GetSystem();

    void SetSystem(String system);

    String GetApplication();

    void SetApplication(String application);

    String GetComponent();

    void SetComponent(String component);

    String GetEvent();

    void SetEvent(String event);

    Priority GetPriority();

    Status GetStatus();

    void SetStatus(Status value);

    String GetDescription();

    void SetDescription(String value);

    HashMap<String, String> GetParameters();

    void GetParameters(HashMap<String, String> parameters);
}

public class LogEntry implements ILogEntry {
    TimeZone _timeZoneInfo;

    Date _occurence;
    String _user;
    String _sessionId;
    String _transactionId;
    Priority _priority;

    String _system;
    String _application;
    String _component;
    String _event;

    Status _status;
    String _description;
    HashMap < String, String > _parameters;

    public TimeZone GetTimeZone() {
        return _timeZoneInfo;
    }
    public Date GetOccurence() {
        return _occurence;
    }

    public String GetUser() {
        return _user;
    }
    public String GetSessionId() {
        return _sessionId;
    }
    public String GetTransactionId() {
        return _transactionId;
    }

    public String GetSystem() {
        return _system;
    }
    public void SetSystem(String system) {
        _system = system;
    }

    public String GetApplication() {
        return _application;
    }
    public void SetApplication(String application) {
        _application = application;
    }

    public String GetComponent() {
        return _component;
    }
    public void SetComponent(String component) {
        _component = component;
    }

    public String GetEvent() {
        return _event;
    }
    public void SetEvent(String event) {
        _event = event;
    }


    public Priority GetPriority() {
        return _priority;
    }

    public Status GetStatus() {
        return _status;
    }
    public void SetStatus(Status value) {
        _status = value;
    }

    public String GetDescription() {
        return _description;
    }
    public void SetDescription(String value) {
        _description = value;
    }

    public HashMap < String, String > GetParameters() {
        return _parameters;
    }
    public void GetParameters(HashMap < String, String > parameters) {
        _parameters = parameters;
    }

    public LogEntry(TimeZone timeZoneInfo, Date occurence,
                    String user, String sessionId, String transactionId, Priority priority) {
        _parameters = new HashMap < String, String > ();

        _timeZoneInfo = timeZoneInfo;
        _occurence = occurence;
        _user = user;
        _sessionId = sessionId;
        _transactionId = transactionId;
        _priority = priority;
    }
}
